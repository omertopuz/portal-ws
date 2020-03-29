package com.portal.ws.model;

public class PdfFileResponse {
    private byte[] blobData;
    private String message;

    public PdfFileResponse(byte[] blobData) {
        this.blobData = blobData;
    }

    public PdfFileResponse() {
    }

    public byte[] getBlobData() {
        return blobData;
    }

    public void setBlobData(byte[] blobData) {
        this.blobData = blobData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
