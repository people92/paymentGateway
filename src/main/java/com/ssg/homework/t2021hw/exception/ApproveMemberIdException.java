package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class ApproveMemberIdException extends RuntimeException{

    private String errorMessage;

    public ApproveMemberIdException() {
        this.errorMessage = "승인 결제 회원ID 아님";
    }
}
