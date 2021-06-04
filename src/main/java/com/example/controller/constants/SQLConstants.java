package com.example.controller.constants;

public class SQLConstants {
    public static final String FIND_ALL_USERS = "SELECT * FROM user;";
    public static final String URL = "jdbc:mysql://localhost:3306/regform?allowPublicKeyRetrieval=true&sslMode=DISABLED&serverTimezone=UTC&user=root&password=Idalog71_7";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = (?);";
    public static final String ADD_NEW_USER = "INSERT INTO user (login, password) VALUES (?, ?);";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = (?);";

    public static final String ADD_NEW_CATEGORY = "INSERT INTO category (name) VALUES (?);";
    public static final String FIND_ALL_CATEGORIES = "SELECT * FROM category;";
    public static final String DELETE_CATEGORY_BY_ID = "DELETE FROM category WHERE id = (?);";

    public static final String ADD_NEW_ACTIVITY = "INSERT INTO activity (name, category_id) VALUES (?, ?);";
    public static final String FIND_ALL_ACTIVITIES = "SELECT * FROM activity INNER JOIN category ON activity.category_id = category.id;";
    public static final String DELETE_ACTIVITY_BY_ID = "DELETE FROM activity WHERE id = (?);";

    public static final String SET_ACTIVITY_FOR_USER = "INSERT INTO user_has_activity (user_id, activity_id) VALUES (?, ?);";
    public static final String FIND_USERS_ACTIVITIES = "SELECT * FROM mydb.user_has_activity " +
            "INNER JOIN activity ON activity.id = user_has_activity.activity_id " +
            "INNER JOIN category ON activity.category_id = category.id WHERE user_id = ";

    public static final String FIND_TIME_SPENT = "SELECT * FROM user_has_activity WHERE user_id = ? AND activity_id = ? ;";
    public static final String SET_TIME_SPENT = "UPDATE user_has_activity SET time_spent = ? WHERE user_id = ? AND activity_id = ? ;";
    public static final String SET_UNCONFIRMED_ACTIVITY_FOR_USER = "INSERT INTO unconfirmed_activities (user_id, activity_id, operation) VALUES (?, ?, ?);";
    public static final String FIND_UNCONFIRMED_ACTIVITIES_FOR_USER =
            "SELECT * FROM unconfirmed_activities INNER JOIN activity ON activity.id = unconfirmed_activities.activity_id \n" +
                    "INNER JOIN category ON category_id = category.id\n" +
                    "WHERE user_id = ?;";
    public static final String DELETE_UNCONFIRMED_ACTIVITY_FOR_USER = "DELETE FROM unconfirmed_activities WHERE user_id = (?) AND activity_id = (?);";
    public static final String DELETE_ACTIVITY_FOR_USER = "DELETE FROM user_has_activity WHERE user_id = (?) AND activity_id = (?);";
}
