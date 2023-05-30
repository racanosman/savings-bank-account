package com.racan.api.savings.models.enums;

public enum SavingsAccountStateEnum {

    OPEN("Opened savings account"),
    PENDING("This saving account was not opened in working hours"),
    DOES_NOT_EXIST("Thsi user does not have a savings account");

    private String message;

    SavingsAccountStateEnum(String message) {
        this.message = message;
    }

}
