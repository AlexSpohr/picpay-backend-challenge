package com.projects.picpay.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationResponse {

    private String status;
    private AuthorizationData data;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("data")
    public AuthorizationData getData() {
        return data;
    }

    public void setData(AuthorizationData data) {
        this.data = data;
    }

    public static class AuthorizationData {
        private boolean authorization;

        @JsonProperty("authorization")
        public boolean isAuthorization() {
            return authorization;
        }

        public void setAuthorization(boolean authorization) {
            this.authorization = authorization;
        }
    }
}