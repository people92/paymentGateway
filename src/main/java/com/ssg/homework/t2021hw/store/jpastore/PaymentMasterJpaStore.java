package com.ssg.homework.t2021hw.store.jpastore;

import com.ssg.homework.t2021hw.dto.PaymentMaster;
import com.ssg.homework.t2021hw.store.PaymentMasterStore;
import com.ssg.homework.t2021hw.store.jpo.PaymentMasterJpo;
import com.ssg.homework.t2021hw.store.repository.PaymentMasterJpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class PaymentMasterJpaStore implements PaymentMasterStore {

    private PaymentMasterJpaRepository paymentMasterJpaRepository;

    public PaymentMasterJpaStore(PaymentMasterJpaRepository paymentMasterJpaRepository) {
        this.paymentMasterJpaRepository = paymentMasterJpaRepository;
    }

    @Override
    @Cacheable(value="paymentMasterCacheByPmtCode")
    public List<PaymentMaster> retrievePaymentMasterByPmtCode(String pmtCode) {
        return paymentMasterJpaRepository.findByPmtCode(pmtCode).stream()
                .map(PaymentMasterJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value="paymentMasterCacheByPmtCodeAndPmtType")
    public List<PaymentMaster> retrievePaymentMasterByPmtCodeAndPmtType(String pmtCode, String pmtType) {
        return paymentMasterJpaRepository.findByPmtCodeAndPmtType(pmtCode, pmtType).stream()
                .map(PaymentMasterJpo::toDomain).collect(Collectors.toList());
    }
}
