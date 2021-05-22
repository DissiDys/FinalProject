package com.example.model.entity;


import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class User {
    private String login;
    private String password;
    private long id;

    public enum ROLE {
        USER, ADMIN, GUEST;

        public static String getStringValue(ROLE role) {
            switch (role) {
                case ADMIN:
                    return "admin";
                case USER:
                    return "user";
            }
            return "unknown";
        }
    }

    private User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User createUser(String login, String password) {
        return new User(login, password);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return login.equals(((User)obj).login) && password.equals(((User)obj).password);
        }
        return false;
    }
}
