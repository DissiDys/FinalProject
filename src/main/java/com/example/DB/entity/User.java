package com.example.DB.entity;


import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class User {
    private long hashedLogin;
    private long hashedPassword;
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

    public User(long name, long password) {
        this.hashedLogin = name;
        this.hashedPassword = password;
    }

    public static User createUser(String login, String password) {
        long hashLogin = Hashing.sha256()
                .hashString(login, StandardCharsets.UTF_8)
                .asLong();
        long hashPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .asLong();
        return new User(hashLogin, hashPassword);
    }

    public long getHashedLogin() {
        return hashedLogin;
    }

    public long getHashedPassword() {
        return hashedPassword;
    }

    public long getId() {
        return id;
    }

    public void setHashedLogin(long hashedLogin) {
        this.hashedLogin = hashedLogin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHashedPassword(long hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return hashedLogin == ((User)obj).hashedLogin && hashedPassword == ((User)obj).hashedPassword;
        }
        return false;
    }
}
