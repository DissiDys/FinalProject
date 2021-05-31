package com.example.model.service.UserService;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.User;

public class SendRequestToAddActivityService {
    public static void sendRequest(User user, Activity activity) {
        try(UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.sendRequestToAddActivity(user, activity);
        }
    }
}
