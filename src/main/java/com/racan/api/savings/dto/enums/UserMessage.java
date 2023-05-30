package com.racan.api.savings.dto.enums;

public enum UserMessage implements MessageEnums {

    USER_DOES_NOT_EXIST("User does not exist", 9);

    private String message;
    private int code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    UserMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
