package com.example.model.service;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.User;

public class LoginService {
    public static boolean DBContainsUser(User user) {
        UserDao dao = JDBCDaoFactory.getInstance().createUserDao();
        for (User u : dao.findAll()) {
            if (user.getLogin().equals(u.getLogin()) && user.getPassword().equals(u.getPassword())) {
                user.setId(u.getId());
                dao.close();
                return true;
            }
        }
        dao.close();
        return false;
    }
}
