package com.example.model.service.UserService;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.User;

import java.util.List;

public class UserActivitiesListService {
    public static List<Activity> getUserActivitiesList(User user) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findUsersActivities(user);
        }
    }
}
