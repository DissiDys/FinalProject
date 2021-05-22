package com.example.model.service;

import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.User;

public class LoginService {
    public static boolean DBContainsUser(User user) {
        for (User u : JDBCDaoFactory.getInstance().createUserDao().findAll()) {
            if (user.getLogin().equals(u.getLogin())) {
                user.setId(u.getId());
                return true;
            }
        }
        return false;
    }
}
