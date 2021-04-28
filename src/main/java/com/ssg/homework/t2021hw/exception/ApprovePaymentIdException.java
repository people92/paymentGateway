package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class ApprovePaymentIdException extends RuntimeException{
    private String errorMessage;

    public ApprovePaymentIdException() {
        this.errorMessage = "승인 결제ID 내역이 없음";
    }
}
