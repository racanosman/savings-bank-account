package com.racan.api.savings.dao.savings;

import com.racan.api.savings.models.User;
import com.racan.api.savings.models.enums.SavingsAccountStateEnum;

public interface SavingsDAO {

    boolean hasSavingAccount(User user);
    SavingsAccountStateEnum getAccountState(User user);
    double getBalance(User user);
    boolean updateAccountValue(User user, double amount);
    boolean createAccount(User user, SavingsAccountStateEnum status);

}
