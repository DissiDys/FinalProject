package com.example.model.service.AdminService.userServices;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;

public class DeleteUserService {
    public static void deleteUser(int id){
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.delete(id);
        }
    }
}
