package com.ssg.homework.t2021hw.service;

import com.ssg.homework.t2021hw.dto.PaymentDto;
import com.ssg.homework.t2021hw.dto.PaymentMaster;

import java.util.List;

public interface PaymentGatewayService {



    PaymentDto approve(String mbrId, String pmtCode, String pmtType, long pmtAmt);

    PaymentDto cancel(String mbrId, String bfPmtId, long pmtAmt);

    PaymentDto partialCancel(String mbrId, String bfPmtId, long pmtAmt);

    List<PaymentDto> getRecentPaymentList(String mbrId, String succYn, Integer size);

    int countPaymentBySuccYn(String succYn);

}
