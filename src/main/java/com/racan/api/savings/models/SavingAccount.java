package com.racan.api.savings.models;

import com.racan.api.savings.models.enums.SavingsAccountStateEnum;

public class SavingAccount {

    private double amount;
    private SavingsAccountStateEnum status;

    public SavingAccount(double amount, SavingsAccountStateEnum status) {
        this.amount = amount;
        this.status = status;
    }

    public SavingAccount(double amount) {
        this.amount = amount;
        this.status = SavingsAccountStateEnum.OPEN;
    }

    public void setStatus(SavingsAccountStateEnum status) {
        this.status = status;
    }

    public SavingsAccountStateEnum getStatus() {
        return status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

}
