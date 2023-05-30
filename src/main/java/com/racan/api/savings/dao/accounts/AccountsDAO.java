package com.racan.api.savings.dao.accounts;

import com.racan.api.savings.models.User;

import java.util.Optional;

public interface AccountsDAO {

    Optional<User> getUser(String password, String userName);

}
