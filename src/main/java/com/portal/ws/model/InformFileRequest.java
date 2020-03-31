package com.portal.ws.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class InformFileRequest {

    @NotEmpty
    private UUID fileMetaDataId;
    @NotEmpty
    private String fileName;
    @Min(value = 0,message = "Entity Id must be greater than 0.")
    private int relatedEntityId;
    @Pattern(regexp = "^[0-9]$",message = "File id must be numeric.")
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
