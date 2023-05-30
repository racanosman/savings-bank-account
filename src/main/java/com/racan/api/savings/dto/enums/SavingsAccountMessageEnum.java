package com.racan.api.savings.dto.enums;

public enum SavingsAccountMessageEnum implements MessageEnums {

    OPEN("User has opened a savings account", 1),
    CREATED("This user already has a savings account", 2),
    DOES_NOT_EXIST("The user does not have a savings account", 3),
    PENDING("This account was not opened in working hours", 4),
    NOT_ENOUGH_MONEY("This user cannot withdraw the desired amount", 5),
    SUCCESSFUL_DEPOSIT("User has made a successful deposit", 6),
    SUCCESSFUL_WITHDRAWAL("User has made a successful withdrawal", 7),
    ACCOUNT_IS_PENDING("User has made the opening action outside of working hours", 8);

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

    SavingsAccountMessageEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
