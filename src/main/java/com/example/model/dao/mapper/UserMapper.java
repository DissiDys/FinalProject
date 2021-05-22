package com.example.model.dao.mapper;

import com.example.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User>{
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = User.createUser(rs.getString(2), rs.getString(3));
        user.setId(rs.getLong(1));
        return user;
    }
}
