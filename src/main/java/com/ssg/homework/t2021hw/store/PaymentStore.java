package com.ssg.homework.t2021hw.store;

import com.ssg.homework.t2021hw.dto.PaymentDto;

import java.util.List;

public interface PaymentStore {

    List<PaymentDto> retrieveRecentPaymentList(String mbrId, String succYn, Integer size);

    PaymentDto retrieveApprovePayment(String pmtId);

    String savePaymentList(PaymentDto paymentDto);

    int countPaymentBySuccYn(String succYn);
}
