package com.example.model.service;

import com.example.model.dao.DaoFactory;
import com.example.model.dao.UserDao;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public int getTimeByActivity(User user, Activity activity) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            return dao.getActivitySpentTime(user, activity);
        }
    }

    public void sendRequest(User user, Activity activity, Operation operation) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            dao.setUnconfirmedActivityForUser(user, activity, operation);
        }
    }

    public void setSpentTime(User user, Activity activity, int minutes) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            dao.setActivitySpentTime(user, activity, minutes);
        }
    }

    public List<Activity> getUserActivitiesList(User user) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            return dao.findUsersActivities(user);
        }
    }
}
