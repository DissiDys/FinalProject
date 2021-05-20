package com.example.model;

import com.example.model.DB.Manage.DBManager;
import com.example.model.DB.entity.User;

public class RegistrationService {
    public static boolean regNewUser(String login, String password){
        return DBManager.getInstance().insertUser(User.createUser(login, password));
    }
}
