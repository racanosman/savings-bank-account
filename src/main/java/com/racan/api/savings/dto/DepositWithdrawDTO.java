package com.racan.api.savings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepositWithdrawDTO {

    private double amount;
    private String type;

    public DepositWithdrawDTO(@JsonProperty("amount") double amount, @JsonProperty("type") String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

}
