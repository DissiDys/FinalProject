package com.example.model.dao.mapper;

import com.example.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User>{
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = User.createUser(rs.getString("login"), rs.getString("password"));
        user.setId(rs.getLong("user.id"));
        return user;
    }
}
