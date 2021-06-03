package com.ssg.homework.t2021hw.service.logic;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.dto.PaymentMaster;
import com.ssg.homework.t2021hw.dto.RandomVirtualServer;
import com.ssg.homework.t2021hw.exception.*;
import com.ssg.homework.t2021hw.service.PaymentGatewayService;
import com.ssg.homework.t2021hw.store.MemberStore;
import com.ssg.homework.t2021hw.store.PaymentMasterStore;
import com.ssg.homework.t2021hw.store.PaymentStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

@Slf4j
@Service
public class PaymentGatewayLogic implements PaymentGatewayService {

    private MemberStore memberStore;
    private PaymentStore paymentStore;
    private PaymentMasterStore paymentMasterStore;

    public PaymentGatewayLogic(MemberStore memberStore, PaymentStore paymentStore, PaymentMasterStore paymentMasterStore){
        this.memberStore = memberStore;
        this.paymentStore = paymentStore;
        this.paymentMasterStore = paymentMasterStore;
    }



    /*
    * PaymentGateway 공통 로직
    * */

    public void pgCommonLogicExecute(String mbrId, String pmtCode, long pmtAmt) {
        log.info("====== PG 공통로직 Start=======");
        //필수값 체크 로직
        if(StringUtils.isEmpty(mbrId) || StringUtils.isEmpty(pmtCode)){
            log.error("필수값 없음");
            throw new RequiredFieldNameException();
        }
        // 등록된 회원ID 체크 로직
        if(!memberStore.existMemberId(mbrId)){
            log.error("등록된 회원 ID 없음");
            throw new MemberIdException();
        }
        // 결제금액 오류 체크 (항상 양수)
        if(pmtAmt <= 0){
            log.error("결재금액 음수");
            throw new PaymentAmountException();
        }
        log.info("======PG 공통로직 End=======");
        return;
    }


    /*
    *  결재방법 공통 로직
    * */
    public void paymentCodeCommonLogicExecute(PaymentDto paymentDto){
        
        log.info("======결재방법 공통로직 Start=======");
        
        //PAYMENT_MST Table 조회
        List<PaymentMaster> paymentMasterList = new ArrayList<>();
        //결재코드만 주어진 경우
        if(StringUtils.isEmpty(paymentDto.getPmtType())){
            log.info("결재코드로 조회");
            paymentMasterList = paymentMasterStore.retrievePaymentMasterByPmtCode(paymentDto.getPmtCode());
        } else { //결재코드 + 결재 타입 주어진 경우
            log.info("결재코드+결재타입으로 조회");
            paymentMasterList = paymentMasterStore.retrievePaymentMasterByPmtCodeAndPmtType(paymentDto.getPmtCode(), paymentDto.getPmtType());
        }

        log.info("PAYMENT_MST 테이블 조회 건수 : {}", paymentMasterList.size());

        //매칭되는 결제코드,결제타입이 없음
        if(paymentMasterList.size() == 0){
            throw new MatchingCodeTypeException();
        }

        PaymentMaster paymentMaster = null;

        //결재코드 + 결재 타입 주어진 경우
        if(!StringUtils.isEmpty(paymentDto.getPmtType())){
            for(PaymentMaster x : paymentMasterList){
                if(paymentDto.getPmtType().equals(x.getPmtType())){
                    paymentMaster = x;
                }
            }
        } else { //결재코드만 주어진 경우
            //결재타입이 null이 있는지 체크
            boolean checkPaymentMethod = paymentMasterList.stream().allMatch(x -> x.getPmtType() == null);
            if(!checkPaymentMethod) {
                //결재타입 랜덤 선택(결재코드에 대한 마스터 갯수만큼)
                log.info("결재타입 랜덤으로 조회");
                Random random = new Random();
                int randomValue = random.nextInt(paymentMasterList.size());
                paymentMaster = paymentMasterList.get(randomValue);
                log.info("결재타입 => {}", paymentMaster.getPmtType());
            }else {
                //결재타입이 null인 경우는 무조건 1개 방식으로 처리
                paymentMaster = paymentMasterList.get(0);
            }
        }

        //MASTER 테이블 조회한 결과 PMT_TYPE null이 아닐 경우
        if(!StringUtils.isEmpty(paymentMaster.getPmtType()))
            paymentDto.setPmtType(paymentMaster.getPmtType());

        log.info("======결재방법 공통로직 End=======");

        return;
    }

    /*
    결재서버 통신 수행 로직
    *
    * */
    public void connectPaymentServer(PaymentDto paymentDto) {

        log.info("======결재서버 통신 수행 Start=======");

        //결제서버 통신 수행
        //가상 결재 서버 클래스 8:2(성공:실패) 비율 : 랜덤 함수 이용
        RandomVirtualServer randomVirtualServer = new RandomVirtualServer();

        String isSuccess = randomVirtualServer.connectVirtualSever();

        //결재서버 성공
        if("Y".equals(isSuccess)){
            log.info("결재서버 통신 성공");
            paymentDto.setSuccYn("Y");
            paymentDto.setSuccMsg("처리완료되었습니다");
        }
        //결재서버 실패
        else {
            log.info("결재서버 통신 실패");
            paymentDto.setSuccYn("N");
        }

        //승인시간 저장
        paymentDto.setAprvTime(new Timestamp(System.currentTimeMillis()));

        //결재내역 저장
        log.info("결재내역 저장");
        String pmtId = paymentStore.savePaymentList(paymentDto);

        paymentDto.setPmtId(pmtId);

        log.info("======결재서버 통신 수행 End=======");

    }
    @Override
    public PaymentDto approve(String mbrId, String pmtCode, String pmtType, long pmtAmt) {

        log.info("======결재승인 Start=======");

        //PaymentGateway 공통 로직
        this.pgCommonLogicExecute(mbrId, pmtCode, pmtAmt);

        //결재 승인시 DTO 생성
        PaymentDto paymentDto = new PaymentDto(mbrId, pmtCode, pmtType, pmtAmt, "10");

        paymentDto.setRegpeId("YUN");
        paymentDto.setModpeId("YUN");

        //결재방법 공통로직 수행
        this.paymentCodeCommonLogicExecute(paymentDto);

        //결재서버 통신 수행
        this.connectPaymentServer(paymentDto);

        log.info("======결재승인 End=======");

        return paymentDto;
    }

    @Override
    public PaymentDto cancel(String mbrId, String bfPmtId, long pmtAmt) {

        log.info("======결재취소 Start=======");

        //PaymentGateway 공통 로직
        this.pgCommonLogicExecute(mbrId, bfPmtId, pmtAmt);

        //결재ID로 결재내역 테이블 조회
        PaymentDto paymentDto = paymentStore.retrieveApprovePayment(bfPmtId);

        //결재ID 미존재시 에러 발생
        if(paymentDto == null) throw new ApprovePaymentIdException();

        //승인 결재 회원 ID 불일치
        if(!mbrId.equals(paymentDto.getMbrId())) throw new ApproveMemberIdException();

        long approveAmt = paymentDto.getPmtAmt();

        //승인결재금액과 취소결재금액 다를시 에러 발생
        if(approveAmt != pmtAmt) throw new DifferenceCancelAmountException();

        //결재 취소시 DTO 생성
        PaymentDto cancelPaymentDto = new PaymentDto(mbrId, paymentDto.getPmtCode(), paymentDto.getPmtType(), pmtAmt, "20", bfPmtId);

        //결재서버 통신 수행
        this.connectPaymentServer(cancelPaymentDto);

        log.info("======결재취소 End=======");

        return cancelPaymentDto;
    }

    @Override
    public PaymentDto partialCancel(String mbrId, String bfPmtId, long pmtAmt) {

        log.info("======결재부분취소 Start=======");

        //PaymentGateway 공통 로직
        this.pgCommonLogicExecute(mbrId, bfPmtId, pmtAmt);

        //결재ID로 결재내역 테이블 조회
        PaymentDto paymentDto = paymentStore.retrieveApprovePayment(bfPmtId);

        //결재ID 미존재시 에러 발생
        if(paymentDto == null) throw new ApprovePaymentIdException();

        //승인 결재 회원 ID 불일치
        if(!mbrId.equals(paymentDto.getMbrId())) throw new ApproveMemberIdException();

        long partialCancelAmt = paymentDto.getPmtAmt() - pmtAmt;

        //승인결재금액보다 취소결재금액 클시  에러 발생
        if(partialCancelAmt < 0) throw new PartialCancelAmountException();

        //결재 부분취소시 DTO 생성
        PaymentDto partialCancelPaymentDto = new PaymentDto(mbrId, paymentDto.getPmtCode(), paymentDto.getPmtType(), pmtAmt, "20", bfPmtId);

        StringBuffer sb = new StringBuffer();

        //부분취소가능여부 조회(결재타입 존재할시 안할시)
        if(StringUtils.isEmpty(paymentDto.getPmtType())){
            sb.append(paymentMasterStore.retrievePaymentMasterByPmtCode(paymentDto.getPmtCode()).get(0).getPartCnclYn());
        } else {
            sb.append(paymentMasterStore.retrievePaymentMasterByPmtCodeAndPmtType(paymentDto.getPmtCode(), paymentDto.getPmtType()).get(0).getPartCnclYn());
        }
        log.info("부분 취소 가능 여부 => {}", sb.toString());
        //부분취소 불가능 결재타입일 경우 에러 발생
        if("N".equals(sb.toString())) throw new PartialPaymentCancelException();

        //결재서버 통신 수행
        this.connectPaymentServer(partialCancelPaymentDto);

        log.info("======결재부분취소 End=======");

        return partialCancelPaymentDto;
    }

    @Override
    public List<PaymentDto> getRecentPaymentList(String mbrId, String succYn, Integer size) {

        List<PaymentDto> paymentDtoList = paymentStore.retrieveRecentPaymentList(mbrId, succYn, size);

        log.info("결재내역 조회 건수 : {}", paymentDtoList.size());

        if(paymentDtoList.size() == 0) throw new NoPaymentListException();

        return paymentDtoList;
    }

    @Override
    public int countPaymentBySuccYn(String succYn) {
        return paymentStore.countPaymentBySuccYn(succYn);
    }
}
