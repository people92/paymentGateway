package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class PartialPaymentCancelException extends RuntimeException{

    private String errorMessage;

    public PartialPaymentCancelException() {
        this.errorMessage = "부분 취소 불가 결제코드";
    }
}
