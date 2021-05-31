package com.example.model.service.UserService;

import com.example.model.dao.UserDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.User;

import java.sql.Time;

public class GetSpentTimeByActivityService {
    public static Time getTimeByActivity(User user, Activity activity) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            return dao.getActivitySpentTime(user, activity);
        }
    }
}
