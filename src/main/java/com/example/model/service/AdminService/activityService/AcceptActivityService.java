package com.example.model.service.AdminService.activityService;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.User;

public class AcceptActivityService {
    public static void acceptActivity(User user, Activity activity) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.deleteUnconfirmedActivityForUser(user, activity);
            dao.setActivityForUser(user, activity);
        }
    }

    public static void deleteActivity(User user, Activity activity) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.deleteUnconfirmedActivityForUser(user, activity);
        }
    }
}
