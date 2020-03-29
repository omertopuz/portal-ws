package com.portal.ws.model;

public class InfromStateResponse {
    private String result;
    private String message;

    public InfromStateResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public InfromStateResponse() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
