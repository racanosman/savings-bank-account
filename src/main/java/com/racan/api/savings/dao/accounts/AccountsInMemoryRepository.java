package com.racan.api.savings.dao.accounts;

import com.racan.api.savings.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("inMemoryAccountsDB")
public class AccountsInMemoryRepository implements AccountsDAO {

    private static List<User> users = new ArrayList<User>() {{
        add(new User("John", "12345"));
        add(new User("Sara", "123456"));
    }};

    @Override
    public Optional<User> getUser(String password, String username) {
        return users.stream().filter(user -> {
                    if (user.getName().equals(username) && user.getPassword().equals(password)) {
                        return true;
                    }
                    return false;
                }
        ).findFirst();
    }

}
