package com.example.service;
import com.example.DB.manage.DBManager;
import com.example.DB.entity.User;

public class LoginService {
    public static boolean DBContainsUser(String name, String password){
        return DBManager.getInstance().DBContainUser(User.createUser(name, password));
    }
}
