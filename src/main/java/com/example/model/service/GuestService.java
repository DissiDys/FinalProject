package com.example.model.service;

import com.example.model.dao.DaoFactory;
import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.entity.User;

public class GuestService {
    DaoFactory daoFactory = DaoFactory.getInstance("db");

    public boolean DBContainsUser(User user) {
        UserDao dao = daoFactory.createUserDao();
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

    public boolean regNewUser(String login, String password) throws NotUniqueInsertionException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(User.createUser(login, password));
        }
    }
}
