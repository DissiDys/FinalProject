package com.example.model.dao;

import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    void setActivityForUser(User user, Activity activity);

    void deleteActivityForUser(User user, Activity activity);

    List<Activity> findUsersActivities(User user);

    int getActivitySpentTime(User user, Activity activity);

    void setActivitySpentTime(User user, Activity activity, int minutes);

    void setUnconfirmedActivityForUser(User user, Activity activity, Operation operation);

    List<Activity> getUnconfirmedActivitiesForUser(User user);

    void deleteUnconfirmedActivityForUser(User user, Activity activity);

    Optional<Operation> getOperationForUserUnconfirmedActivity(User user, Activity activity);
}
