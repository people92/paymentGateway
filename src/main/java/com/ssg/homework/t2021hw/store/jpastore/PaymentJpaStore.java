package com.ssg.homework.t2021hw.store.jpastore;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.store.PaymentStore;
import com.ssg.homework.t2021hw.store.jpo.PaymentJpo;
import com.ssg.homework.t2021hw.store.repository.PaymentJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class PaymentJpaStore implements PaymentStore {

    private PaymentJpaRepository paymentJpaRepository;

    public PaymentJpaStore(PaymentJpaRepository paymentJpaRepository){
        this.paymentJpaRepository = paymentJpaRepository;
    }
    @Override
    public List<PaymentDto> retrieveRecentPaymentList(String mbrId, String succYn, Integer size) {
        
        //size 미 입력시 15로 세팅
        Integer cnt = size == null ? 15 : size;

        if("Y".equals(succYn)){
            return paymentJpaRepository.findByMbrIdAndSuccYnOrderByAprvTimeDesc(mbrId, succYn, PageRequest.of(0, cnt))
                    .stream().map(PaymentJpo::toDomain).collect(Collectors.toList());
        } else {
            return paymentJpaRepository.findByMbrIdOrderByAprvTimeDesc(mbrId, PageRequest.of(0, cnt))
                    .stream().map(PaymentJpo::toDomain).collect(Collectors.toList());
        }

    }

    @Override
    public PaymentDto retrieveApprovePayment(String pmtId) {
        Optional<PaymentJpo> paymentJpo = paymentJpaRepository.findByPmtIdAndAprvType(pmtId,"10");
        if(paymentJpo.isPresent()){
            return paymentJpo.get().toDomain();
        }
        return null;
    }

    @Override
    public String savePaymentList(PaymentDto paymentDto) {
        PaymentJpo paymentJpo = new PaymentJpo(paymentDto);

        paymentJpaRepository.save(paymentJpo);

        return paymentJpo.getPmtId();

    }

    @Override
    public int countPaymentBySuccYn(String succYn) {
        return paymentJpaRepository.countBySuccYn(succYn);
    }
}
