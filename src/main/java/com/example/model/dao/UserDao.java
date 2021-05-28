package com.example.model.dao;

import com.example.model.entity.Activity;
import com.example.model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{
     void setActivityForUser(User user, Activity activity);
     List<Activity> findUsersActivities(User user);
}
