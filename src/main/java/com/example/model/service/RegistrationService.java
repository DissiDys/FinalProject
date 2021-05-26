package com.example.model.service;

import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.User;

public class RegistrationService {
    public static boolean regNewUser(String login, String password) throws NotUniqueInsertionException {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            return dao.create(User.createUser(login, password));
        } catch (NotUniqueInsertionException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
