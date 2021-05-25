package com.example.model.service.userServices;

import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.User;

import java.util.List;

public class UsersListService {
    public static List<User> getUsersList(){
        return JDBCDaoFactory.getInstance().createUserDao().findAll();
    }
}
