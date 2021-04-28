package com.ssg.homework.t2021hw.store.repository;

import com.ssg.homework.t2021hw.store.jpo.PaymentJpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpo, String> {

    List<PaymentJpo> findByMbrIdOrderByAprvTimeDesc(String mbrId, Pageable pageable);

    List<PaymentJpo> findByMbrIdAndSuccYnOrderByAprvTimeDesc(String mbrId, String succYn, Pageable pageable);

    Optional<PaymentJpo> findByPmtIdAndAprvType(String pmtId, String aprvType);

    int countBySuccYn(String succYn);
}
