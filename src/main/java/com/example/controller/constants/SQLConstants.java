package com.example.controller.constants;

public class SQLConstants {
    public static final String FIND_ALL_USERS = "SELECT * FROM user;";
    public static final String URL = "jdbc:mysql://localhost:3306/regform?allowPublicKeyRetrieval=true&sslMode=DISABLED&serverTimezone=UTC&user=root&password=Idalog71_7";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = (?);";
    public static final String ADD_NEW_USER = "INSERT INTO user (login, password) VALUES (?, ?);";
}
