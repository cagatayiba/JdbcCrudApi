package com.cagatayiba.coursePlatform.dto;


public class DefaultErrorMessage {
    private String status;
    private String reasonMessage;

    public DefaultErrorMessage(String reasonMessage){
        this.reasonMessage = reasonMessage;
        this.status = "FAILED";
    }
}
