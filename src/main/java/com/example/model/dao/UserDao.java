package com.example.model.dao;

import com.example.model.entity.Activity;
import com.example.model.entity.User;

import java.sql.Time;
import java.util.List;

public interface UserDao extends GenericDao<User>{
     void setActivityForUser(User user, Activity activity);
     List<Activity> findUsersActivities(User user);
     Time getActivitySpentTime(User user, Activity activity);
     void setActivitySpentTime(User user, Activity activity, Time time);
     void setUnconfirmedActivityForUser(User user, Activity activity);
     List<Activity> getUnconfirmedActivitiesForUser(User user);
     void deleteUnconfirmedActivityForUser(User user, Activity activity);
}
