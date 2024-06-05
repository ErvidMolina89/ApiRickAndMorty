package com.wposs.apirickandmorty.Models;

public class MessageResponse extends BaseModel {
    private final int Code;
    private final String Message;

    public MessageResponse(int code, String message) {
        this.Code = code;
        this.Message = message;
    }

    public int getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }
}
