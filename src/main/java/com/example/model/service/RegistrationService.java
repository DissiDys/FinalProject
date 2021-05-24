package com.example.model.service;

import com.example.model.dao.exception.NotUniqueLoginException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.User;

public class RegistrationService {
    public static boolean regNewUser(String login, String password) throws NotUniqueLoginException {
        try {
            return JDBCDaoFactory.getInstance().createUserDao().create(User.createUser(login, password));
        } catch (NotUniqueLoginException e) {
            throw e;
        }
    }
}
