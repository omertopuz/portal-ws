package com.portal.ws.model;

public class PdfFileRequest {
    private int entityId;
    private String entityType;

    public PdfFileRequest(int entityId, String entityType) {
        this.entityId = entityId;
        this.entityType = entityType;
    }

    public PdfFileRequest() {
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
