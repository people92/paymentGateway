package com.ssg.homework.t2021hw.store.repository;

import com.ssg.homework.t2021hw.store.jpo.PaymentMasterJpo;
import com.ssg.homework.t2021hw.store.jpo.PaymentMasterPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMasterJpaRepository extends JpaRepository<PaymentMasterJpo, PaymentMasterPK> {

    List<PaymentMasterJpo> findByPmtCode(String pmtCode);

    List<PaymentMasterJpo> findByPmtCodeAndPmtType(String pmtCode, String pmtType);
}
