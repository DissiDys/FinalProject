package com.example.model.service;

import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.User;

public class RegistrationService {
    public static boolean regNewUser(String login, String password){
        return JDBCDaoFactory.getInstance().createUserDao().create(User.createUser(login, password));
    }
}
