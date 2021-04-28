package com.ssg.homework.t2021hw.controller;

import com.ssg.homework.t2021hw.exception.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PaymentGatewayExceptionHandler {


    @ExceptionHandler(RequiredFieldNameException.class)
    public ResponseEntity<String> requiredFieldNameException(RequiredFieldNameException e) {
        ResponseEntity responseEntity = ResponseEntity.status(401).body(e.getErrorMessage());

        return responseEntity;
    }

    @ExceptionHandler(MemberIdException.class)
    public ResponseEntity<String> memberIdException(MemberIdException e) {
        ResponseEntity responseEntity = ResponseEntity.status(401).body(e.getErrorMessage());

        return responseEntity;
    }
    @ExceptionHandler(PaymentAmountException.class)
    public ResponseEntity<String> paymentAmoutException(PaymentAmountException e) {
        ResponseEntity responseEntity = ResponseEntity.status(401).body(e.getErrorMessage());

        return responseEntity;
    }
    @ExceptionHandler(MatchingCodeTypeException.class)
    public ResponseEntity<String> matchingCodeTypeException(MatchingCodeTypeException e) {
        ResponseEntity responseEntity = ResponseEntity.status(404).body(e.getErrorMessage());

        return responseEntity;
    }
    @ExceptionHandler(ApprovePaymentIdException.class)
    public ResponseEntity<String> approvePaymentIdException(ApprovePaymentIdException e) {
        ResponseEntity responseEntity = ResponseEntity.status(404).body(e.getErrorMessage());

        return responseEntity;
    }
    @ExceptionHandler(DifferenceCancelAmountException.class)
    public ResponseEntity<String> differenceCancelAmountException(DifferenceCancelAmountException e) {
        ResponseEntity responseEntity = ResponseEntity.status(404).body(e.getErrorMessage());

        return responseEntity;
    }
    @ExceptionHandler(PartialPaymentCancelException.class)
    public ResponseEntity<String> partialPaymentCancelException(PartialPaymentCancelException e) {
        ResponseEntity responseEntity = ResponseEntity.status(401).body(e.getErrorMessage());

        return responseEntity;
    }

    @ExceptionHandler(ApproveMemberIdException.class)
    public ResponseEntity<String> approveMemberIdException(ApproveMemberIdException e) {
        ResponseEntity responseEntity = ResponseEntity.status(401).body(e.getErrorMessage());

        return responseEntity;
    }
    @ExceptionHandler(NoPaymentListException.class)
    public ResponseEntity<String> noPaymentListException(NoPaymentListException e) {
        ResponseEntity responseEntity = ResponseEntity.status(404).body(e.getErrorMessage());

        return responseEntity;
    }
    @ExceptionHandler(PartialCancelAmountException.class)
    public ResponseEntity<String> partialCancelAmountException(PartialCancelAmountException e) {
        ResponseEntity responseEntity = ResponseEntity.status(404).body(e.getErrorMessage());

        return responseEntity;
    }
}
