package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class PaymentAmountException extends RuntimeException {

    private String errorMessage;

    public PaymentAmountException() {
        this.errorMessage = "결재금액 오류";
    }
}
