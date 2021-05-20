package com.example.model.DB.Manage;

import com.example.model.DB.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager dbManager;
    private final String url;

    private DBManager() {
        url = "jdbc:mysql://localhost:3306/regform?allowPublicKeyRetrieval=true&sslMode=DISABLED&serverTimezone=UTC&user=root&password=Idalog71_7";
    }

    public static DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection(String connectionUrl) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(connectionUrl);
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = getConnection(url);
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(Constants.FIND_ALL_USERS)
        ) {
            while (resultSet.next()) {
                User userForList = new User(resultSet.getLong(2), resultSet.getLong(3));
                userForList.setId(resultSet.getLong(1));
                users.add(userForList);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return users;
    }

    public boolean insertUser(User user) {
        boolean res = false;

        try (Connection con = getConnection(url);
             PreparedStatement pstmt = con.prepareStatement(Constants.ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;

            pstmt.setString(i++, String.valueOf(user.getHashedLogin()));
            pstmt.setString(i++, String.valueOf(user.getHashedPassword()));
            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
                res = true;
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }

        return res;
    }

    public boolean DBContainUser(User user) {
        for (User u : findAllUsers()) {
            if (u.equals(user)) return true;
        }
        return false;
    }
}
