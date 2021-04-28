package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class MemberIdException extends RuntimeException {

    private String errorMessage;

    public MemberIdException() {
        this.errorMessage = "등록된 회원ID 없음";
    }
}
