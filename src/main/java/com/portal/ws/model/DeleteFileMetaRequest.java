package com.portal.ws.model;

import javax.validation.constraints.Pattern;

public class DeleteFileMetaRequest {
    @Pattern(regexp = "^[0-9]$",message = "File id must be numeric.")
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
