package com.racan.api.savings.services;

import com.racan.api.savings.dao.accounts.AccountsDAO;
import com.racan.api.savings.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final AccountsDAO accountsDAO;

    @Autowired
    public UserService(@Qualifier("inMemoryAccountsDB") AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public Optional<User> getUserNameAndPassWord(String user, String password) {
        return accountsDAO.getUser(password, user);
    }

}
