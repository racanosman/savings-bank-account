package com.racan.api.savings.models;

import java.util.UUID;

public class User {

    private final String name;
    private final UUID userID;
    private final String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.userID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

}
