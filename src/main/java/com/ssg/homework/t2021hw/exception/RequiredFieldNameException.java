package com.ssg.homework.t2021hw.exception;

import lombok.Getter;

@Getter
public class RequiredFieldNameException extends RuntimeException  {

    private String errorMessage;

    public RequiredFieldNameException() {
        this.errorMessage = "필수값 없음";
    }
}
