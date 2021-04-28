package com.ssg.homework.t2021hw.controller;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.service.PaymentGatewayService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Payment Gateway Controller
 */
@RestController
@RequestMapping("/api/pg")
public class PaymentGatewayController {

	private PaymentGatewayService paymentGatewayService;

	public PaymentGatewayController(PaymentGatewayService paymentGatewayService){
		this.paymentGatewayService = paymentGatewayService;
	}
	/**
	 * 결제 승인 요청
	 *
	 * @param mbrId 회원ID
	 * @param pmtCode 결제코드
	 * @param pmtType 결제타입
	 * @param pmtAmt 결제금액
	 * @return
	 */
	@ApiOperation(value = "결제 승인 요청", notes = "결제수단에 대한 승인 요청.\n성공,실패 모두 결제내역에 데이터가 생성되며, 생성된 결제내역이 응답결과임.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mbrId", required = true, example = "0000000345", value = "회원ID\n( MEMBER.MBR_ID 컬럼 참고 )"),
		@ApiImplicitParam(name = "pmtCode", required = true, example = "P0002", value = "결제코드\n( PAYMENT_MST.PMT_CODE 컬럼 참고 )", allowableValues = "P0001,P0002,P0003,P0004,P0005,..."),
		@ApiImplicitParam(name = "pmtType", example = "", value = "결제타입 - 결제코드에 결제타입이 존재하는 경우, 이 파라미터를 null 로 설정하면, 지정가능한 결제타입중에서 랜덤 선택됨\n( PAYMENT_MST.PMT_TYPE 컬럼 참고 )", allowableValues = "PT01,PT02,PT03,PT11,PT12", allowEmptyValue = true ),
		@ApiImplicitParam(name = "pmtAmt", required = true, example = "157400", value = "결제금액"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 401, message = "Unauthorized - 필수값 없음. 등록된 회원ID 없음. 결제금액 오류."),
		@ApiResponse(code = 404, message = "Not Found - 매칭되는 결제코드,결제타입이 없음."),
	})
	@PostMapping(value = "/approve")
	public @ResponseBody PaymentDto approve(String mbrId, String pmtCode, String pmtType, long pmtAmt) {

		return paymentGatewayService.approve(mbrId, pmtCode, pmtType, pmtAmt);
	}

	/**
	 * 결제 취소 요청
	 *
	 * @param mbrId 회원ID
	 * @param pmtId 결제ID
	 * @param pmtAmt 결제금액
	 * @return
	 */
	@ApiOperation(value = "결제 취소 요청", notes = "승인된 결제내역에 대한 전체취소 요청.\n성공,실패 모두 결제내역에 데이터가 생성되며, 생성된 결제내역이 응답결과임.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mbrId", required = true, example = "0000000345", value = "회원ID\n( MEMBER.MBR_ID 컬럼 참고 )"),
		@ApiImplicitParam(name = "bfPmtId", required = true, example = "0000000001", value = "승인 결제ID"),
		@ApiImplicitParam(name = "pmtAmt", required = true, example = "157400", value = "결제금액"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 401, message = "Unauthorized - 필수값 없음. 승인 결제 회원ID 아님. 결제금액 오류."),
		@ApiResponse(code = 404, message = "Not Found - 승인 결제ID 내역이 없음. 승인금액과 취소금액이 다름."),
	})
	@PostMapping(value = "/cancel")
	public PaymentDto cancel(String mbrId, String bfPmtId, long pmtAmt) {

		// TODO code
		// TODO code

		return paymentGatewayService.cancel(mbrId, bfPmtId, pmtAmt);
	}

	/**
	 * 결제 부분취소 요청
	 *
	 * @param mbrId 회원ID
	 * @param pmtId 결제ID
	 * @param pmtAmt 결제금액
	 * @return
	 */
	@ApiOperation(value = "결제 부분취소 요청", notes = "승인된 결제내역에 대한 부분취소 요청.\n성공,실패 모두 결제내역에 데이터가 생성되며, 생성된 결제내역이 응답결과임.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mbrId", required = true, example = "0000000345", value = "회원ID\n( MEMBER.MBR_ID 컬럼 참고 )"),
		@ApiImplicitParam(name = "bfPmtId", required = true, example = "0000000001", value = "승인 결제ID"),
		@ApiImplicitParam(name = "pmtAmt", required = true, example = "157400", value = "결제금액"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 401, message = "Unauthorized - 필수값 없음. 승인 결제 회원ID 아님. 부분 취소 불가 결제코드. 결제금액 오류."),
		@ApiResponse(code = 404, message = "Not Found - 승인 결제ID 내역이 없음. 부분취소금액이 충분하지 않음."),
	})
	@PostMapping(value = "/partialCancel")
	public PaymentDto partialCancel(String mbrId, String bfPmtId, long pmtAmt) {

		// TODO code
		// TODO code

		return paymentGatewayService.partialCancel(mbrId, bfPmtId, pmtAmt);
	}

	/**
	 * 최근 결제 내역 조회
	 *
	 * @param mbrId 회원ID
	 * @param succYn 성공여부
	 * @param size 조회건수
	 * @return
	 */
	@ApiOperation(value = "결제 내역 조회", notes = "회원별 결제내역을 최근순으로 조회.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mbrId", required = true, example = "0000000345", value = "회원ID\n( MEMBER.MBR_ID 컬럼 참고 )"),
		@ApiImplicitParam(name = "succYn", example = "Y", value = "성공여부 - 지정하지 않으면 전체 조회"),
		@ApiImplicitParam(name = "size", example = "15", value = "조회건수 - 지정하지 않으면 기본값 15"),
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 401, message = "Unauthorized - 필수값 없음. 등록된 회원ID 없음."),
		@ApiResponse(code = 404, message = "Not Found - 조회된 결제내역이 없음."),
	})
	@GetMapping(value = "/getRecentPaymentList")
	public @ResponseBody List<PaymentDto> getRecentPaymentList(String mbrId, String succYn, Integer size) {

		// TODO code
		// TODO code

		return paymentGatewayService.getRecentPaymentList(mbrId, succYn, size);
	}

}
