package com.example.model.entity;

public class User {
    private String login;
    private String password;
    private long id;

    private User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User createUser(String login, String password) {
        return new User(login, password);
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

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return login.equals(((User) obj).login) && password.equals(((User) obj).password);
        }
        return false;
    }

    public enum ROLE {
        USER, ADMIN, GUEST;
    }
}
