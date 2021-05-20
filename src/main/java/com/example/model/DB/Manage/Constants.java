package com.example.model.DB.Manage;

public class Constants {
    public static final String FIND_ALL_USERS = "SELECT * FROM user;";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = (?);";
    public static final String ADD_NEW_USER = "INSERT INTO user (login, password) VALUES (?, ?);";
}
