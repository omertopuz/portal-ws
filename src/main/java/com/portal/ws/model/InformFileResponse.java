package com.portal.ws.model;

public class InformFileResponse {
    private boolean result;
    private String message;

    public InformFileResponse(boolean result) {
        this.result = result;
    }

    public InformFileResponse() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
