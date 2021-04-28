package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class DifferenceCancelAmountException extends RuntimeException{
    private String errorMessage;

    public DifferenceCancelAmountException() {
        this.errorMessage = "승인금액과 취소금액이 다름";
    }
}
