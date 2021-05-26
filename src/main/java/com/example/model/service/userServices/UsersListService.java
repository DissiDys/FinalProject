package com.example.model.service.userServices;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.User;

import java.util.List;

public class UsersListService {
    public static List<User> getUsersList(){
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findAll();
        }
    }
}
