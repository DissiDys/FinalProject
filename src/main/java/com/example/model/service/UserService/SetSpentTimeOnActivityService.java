package com.example.model.service.UserService;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.User;

import java.sql.Time;

public class SetSpentTimeOnActivityService {
    public static void setSpentTime(User user, Activity activity, Time time) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.setActivitySpentTime(user, activity, time);
        }
    }
}
