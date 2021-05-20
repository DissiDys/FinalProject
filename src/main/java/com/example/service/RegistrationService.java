package com.example.service;

import com.example.DB.manage.DBManager;
import com.example.DB.entity.User;

public class RegistrationService {
    public static boolean regNewUser(String login, String password){
        return DBManager.getInstance().insertUser(User.createUser(login, password));
    }
}
