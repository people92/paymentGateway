package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class NoPaymentListException extends RuntimeException{

    private String errorMessage;

    public NoPaymentListException() {
        this.errorMessage = "조회된 결제내역이 없음";
    }
}
