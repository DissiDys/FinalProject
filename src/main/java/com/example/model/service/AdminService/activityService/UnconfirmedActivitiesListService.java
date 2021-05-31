package com.example.model.service.AdminService.activityService;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.User;

import java.util.List;

public class UnconfirmedActivitiesListService {
    public static List<Activity> getUnconfirmedActivitiesForUser(User user) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            return dao.getUnconfirmedActivitiesForUser(user);
        }
    }
}
