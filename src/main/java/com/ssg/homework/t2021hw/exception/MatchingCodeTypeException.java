package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class MatchingCodeTypeException extends RuntimeException {

    private String errorMessage;

    public MatchingCodeTypeException() {
        this.errorMessage = "매칭되는 결제코드,결제타입이 없음";
    }
}
