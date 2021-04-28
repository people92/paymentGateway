package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class PartialCancelAmountException extends RuntimeException{

    private String errorMessage;

    public PartialCancelAmountException() {
        this.errorMessage = "부분취소금액이 충분하지 않음";
    }
}
