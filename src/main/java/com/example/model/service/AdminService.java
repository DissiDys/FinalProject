package com.example.model.service;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.DaoFactory;
import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;

import java.util.List;
import java.util.Optional;

public class AdminService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    UserService userService = new UserService();

    public void acceptActivity(User user, Activity activity) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            Optional<Operation> optional = getOperationForUserUnconfirmedActivity(user, activity);
            if (optional.isPresent()) {
                Operation op = optional.get();
                String operation = op.toString();
                dao.deleteUnconfirmedActivityForUser(user, activity);
                if (operation.equals("ADD")) {
                    dao.setActivityForUser(user, activity);
                } else {
                    dao.deleteActivityForUser(user, activity);
                }
            }
        }
    }

    public void dontAcceptActivity(User user, Activity activity) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            dao.deleteUnconfirmedActivityForUser(user, activity);
        }
    }

    public List<Activity> getActivitiesList() {
        try (ActivityDao dao = daoFactory.createActivityDao("mydb")) {
            return dao.findAll();
        }
    }

    public Activity getActivityByID(int id) {
        try (ActivityDao dao = daoFactory.createActivityDao("mydb")) {
            return dao.findById(id).orElse(null);
        }
    }

    public void addActivity(Activity activity) throws NotUniqueInsertionException {
        try (ActivityDao dao = daoFactory.createActivityDao("mydb")) {
            dao.create(activity);
        }
    }

    public Category findCategoryById(int id) {
        try (CategoryDao dao = daoFactory.createCategoryDao("mydb")) {
            return dao.findById(id).orElse(null);
        }
    }

    public void dontAcceptActivity(int id) {
        try (ActivityDao dao = daoFactory.createActivityDao("mydb")) {
            dao.delete(id);
        }
    }

    public List<Activity> getUnconfirmedActivitiesForUser(User user) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            return dao.getUnconfirmedActivitiesForUser(user);
        }
    }

    public void addCategory(String name) throws NotUniqueInsertionException {
        Category category = Category.createCategory(name);
        try (CategoryDao dao = daoFactory.createCategoryDao("mydb")) {
            dao.create(category);
        }
    }

    public List<Category> getCategoriesList() {
        try (CategoryDao dao = daoFactory.createCategoryDao("mydb")) {
            return dao.findAll();
        }
    }

    public void deleteCategory(int id) {
        try (CategoryDao dao = daoFactory.createCategoryDao("mydb")) {
            dao.delete(id);
        }
    }

    public void deleteUser(int id) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            dao.delete(id);
        }
    }

    public List<User> getUsersList() {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            return dao.findAll();
        }
    }

    public User getUserByID(int id) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            return dao.findById(id).orElse(null);
        }
    }

    public Optional<Operation> getOperationForUserUnconfirmedActivity(User user, Activity activity) {
        try (UserDao dao = daoFactory.createUserDao("mydb")) {
            return dao.getOperationForUserUnconfirmedActivity(user, activity);
        }
    }

    public int getAmountOfUsersOnActivity(Activity activity) {
        int amountOfUsers = 0;
        for (User user : getUsersList()) {
            for (Activity userActivity : userService.getUserActivitiesList(user)) {
                if (userActivity.getName().equals(activity.getName())) {
                    amountOfUsers++;
                }
            }
        }
        return amountOfUsers;
    }
}
