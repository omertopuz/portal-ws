package com.portal.ws.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class PdfFileRequest {
    @Min(value = 1,message = "id must be greater than 0")
    private int entityId;
    @Pattern(regexp = "YDFirmProgram|YDFirmProgramSupportTypes|ActivityReport|CouncilDesicionForm|YDPaymentApproves|KobiProjectControlForm|ProjectControlForm",message = "Entity type can only be permitted values")
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
