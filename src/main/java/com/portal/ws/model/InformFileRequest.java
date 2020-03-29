package com.portal.ws.model;

import java.util.UUID;

public class InformFileRequest {
    private UUID fileMetaDataId;
    private String fileName;
    private int relatedEntityId;
    private String fileId;

    public InformFileRequest(UUID fileMetaDataId, String fileName, int relatedEntityId, String fileId) {
        this.fileMetaDataId = fileMetaDataId;
        this.fileName = fileName;
        this.relatedEntityId = relatedEntityId;
        this.fileId = fileId;
    }

    public InformFileRequest() {
    }

    public UUID getFileMetaDataId() {
        return fileMetaDataId;
    }

    public void setFileMetaDataId(UUID fileMetaDataId) {
        this.fileMetaDataId = fileMetaDataId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getRelatedEntityId() {
        return relatedEntityId;
    }

    public void setRelatedEntityId(int relatedEntityId) {
        this.relatedEntityId = relatedEntityId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
