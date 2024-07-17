package com.projects.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExistsException extends PicPayException{

    private String detail;

    public WalletDataAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pd.setTitle("Wallet Data Already Exists");
        pd.setDetail(detail);

        return pd;
    }
}
