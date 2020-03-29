package com.portal.ws.model;

public class InformStateRequest {
    private int id;
    private String state;
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
