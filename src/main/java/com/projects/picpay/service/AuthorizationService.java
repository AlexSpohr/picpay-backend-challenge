package com.projects.picpay.service;

import com.projects.picpay.client.AuthorizationClient;
import com.projects.picpay.controller.dto.TransferDto;
import com.projects.picpay.exception.PicPayException;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projects.picpay.client.dto.AuthorizationResponse;

@Service
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer) {
        ResponseEntity<AuthorizationResponse> resp;
        try {
            resp = authorizationClient.isAuthotized();
        } catch (FeignException e) {
            if (e.status() == 403) {
                return false;
            }
            throw new PicPayException();
        }

        if(resp.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return resp.getBody().getData().isAuthorization();
    }
}
