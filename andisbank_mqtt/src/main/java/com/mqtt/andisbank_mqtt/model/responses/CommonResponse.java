package com.mqtt.andisbank_mqtt.model.responses;

public class CommonResponse {
    private int code;
    private String description;

    public CommonResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public CommonResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
