package com.ssg.homework.t2021hw.store;

import com.ssg.homework.t2021hw.dto.PaymentMaster;

import java.util.List;

public interface PaymentMasterStore {

    List<PaymentMaster> retrievePaymentMasterByPmtCode(String pmtCode);

    List<PaymentMaster> retrievePaymentMasterByPmtCodeAndPmtType(String pmtCode, String pmtType);
}
