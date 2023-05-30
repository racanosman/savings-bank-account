package com.racan.api.savings.services;

import com.racan.api.savings.dao.savings.SavingsDAO;
import com.racan.api.savings.dto.enums.MessageEnums;
import com.racan.api.savings.dto.enums.SavingsAccountMessageEnum;
import com.racan.api.savings.models.User;
import com.racan.api.savings.models.enums.SavingsAccountStateEnum;
import com.racan.api.savings.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SavingsService {

    private final SavingsDAO savingsDAO;

    @Autowired
    public SavingsService(@Qualifier("inMemorySavingsDB") SavingsDAO savingsDAO) {
        this.savingsDAO = savingsDAO;
    }

    public MessageEnums openSavingsAccount(User user) {
        if (!savingsDAO.hasSavingAccount(user)) {
            if (DateTimeUtils.isWeekDay() && DateTimeUtils.isWorkingHours()) {
                savingsDAO.createAccount(user, SavingsAccountStateEnum.OPEN);
                return SavingsAccountMessageEnum.OPEN;
            } else {
                savingsDAO.createAccount(user, SavingsAccountStateEnum.PENDING);
                return SavingsAccountMessageEnum.PENDING;
            }
        }
        if(savingsDAO.getAccountState(user) == SavingsAccountStateEnum.PENDING){
            return SavingsAccountMessageEnum.ACCOUNT_IS_PENDING;
        }
        return SavingsAccountMessageEnum.CREATED;
    }

    public MessageEnums withdrawFromSavingsAccount(User user, double amount) {
        double balance = savingsDAO.getBalance(user);
        double leftBalance = balance - amount;
        if (!savingsDAO.hasSavingAccount(user)) {
            return SavingsAccountMessageEnum.DOES_NOT_EXIST;
        }
        if (savingsDAO.getAccountState(user) == SavingsAccountStateEnum.PENDING) {
            return SavingsAccountMessageEnum.ACCOUNT_IS_PENDING;
        }
        if (leftBalance < 0) {
            return SavingsAccountMessageEnum.NOT_ENOUGH_MONEY;
        }
        savingsDAO.updateAccountValue(user, leftBalance);
        return SavingsAccountMessageEnum.SUCCESSFUL_WITHDRAWAL;
    }

    public MessageEnums depositToSavingAccount(User user, double amount) {
        double balance = savingsDAO.getBalance(user);
        double totalBalance = balance + amount;
        if (!savingsDAO.hasSavingAccount(user)) {
            return SavingsAccountMessageEnum.DOES_NOT_EXIST;
        }
        if (savingsDAO.getAccountState(user) == SavingsAccountStateEnum.PENDING) {
            return SavingsAccountMessageEnum.ACCOUNT_IS_PENDING;
        }
        savingsDAO.updateAccountValue(user, totalBalance);
        return SavingsAccountMessageEnum.SUCCESSFUL_DEPOSIT;
    }

    public double getSavingAccountBalanceByUser(User user) {
        if (savingsDAO.hasSavingAccount(user)) {
            return savingsDAO.getBalance(user);
        }
        return 0;
    }

}

