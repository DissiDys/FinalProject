package com.example.model.service.userServices;

import com.example.model.dao.impl.JDBCDaoFactory;

public class DeleteUserService {
    public static void deleteUser(int id){
        JDBCDaoFactory.getInstance().createUserDao().delete(id);
    }
}
