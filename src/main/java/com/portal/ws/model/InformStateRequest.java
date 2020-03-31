package com.portal.ws.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class InformStateRequest {
    @Min(value = 1,message = "id must be greater than 0")
    private int id;
//    @NotEmpty(message = "State description is required")
    @Pattern(regexp = "DraftApplication|Pending|Accepted|Refused|CompletedSuccessfully|Stopped|Expired|Cancelled|RevisedBeforeCouncil|Deleted|ApplicationFormRevision|SupportedProjectRevision|Failed|ObjectedToDecision",message = "State can be permitted values")
    private String state;

    @Pattern(regexp = "^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$",message = "Date must be of format yyyy-MM-dd")
    private String stateUpdateTime;

    public InformStateRequest(int id, String state, String stateUpdateTime) {
        this.id = id;
        this.state = state;
        this.stateUpdateTime = stateUpdateTime;
    }

    public InformStateRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateUpdateTime() {
        return stateUpdateTime;
    }

    public void setStateUpdateTime(String stateUpdateTime) {
        this.stateUpdateTime = stateUpdateTime;
    }
}
