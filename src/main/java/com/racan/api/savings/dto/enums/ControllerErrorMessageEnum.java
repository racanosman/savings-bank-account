package com.racan.api.savings.dto.enums;

public enum ControllerErrorMessageEnum implements MessageEnums {

    NOT_A_VALID_REQUEST_BODY("Invalid request body", 10);

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

    ControllerErrorMessageEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
