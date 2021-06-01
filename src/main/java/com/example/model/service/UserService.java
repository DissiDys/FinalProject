package com.example.model.service;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;

import java.sql.Time;
import java.util.List;

public class UserService {
    public static Time getTimeByActivity(User user, Activity activity) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            return dao.getActivitySpentTime(user, activity);
        }
    }

    public static void sendRequest(User user, Activity activity, Operation operation) {
        try(UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.setUnconfirmedActivityForUser(user, activity, operation);
        }
    }

    public static void setSpentTime(User user, Activity activity, Time time) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.setActivitySpentTime(user, activity, time);
        }
    }

    public static List<Activity> getUserActivitiesList(User user) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findUsersActivities(user);
        }
    }
}
