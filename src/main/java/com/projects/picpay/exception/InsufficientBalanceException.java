package com.projects.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends PicPayException{

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pd.setTitle("Insufficient Balance");

        return pd;
    }
}
