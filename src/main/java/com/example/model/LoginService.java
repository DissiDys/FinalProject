package com.example.model;
import com.example.model.DB.Manage.DBManager;
import com.example.model.DB.entity.User;

public class LoginService {
    public static boolean DBContainsUser(String name, String password){
        return DBManager.getInstance().DBContainUser(User.createUser(name, password));
    }
}
