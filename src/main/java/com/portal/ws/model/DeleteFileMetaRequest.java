package com.portal.ws.model;

public class DeleteFileMetaRequest {
    private String fileId;

    public DeleteFileMetaRequest(String fileId) {
        this.fileId = fileId;
    }

    public DeleteFileMetaRequest() {
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
