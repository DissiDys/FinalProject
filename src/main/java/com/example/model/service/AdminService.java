package com.example.model.service;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.UserDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;

import java.util.List;

public class AdminService {
    public static void acceptActivity(User user, Activity activity) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            String operation = AdminService.getOperationForUserUnconfirmedActivity(user, activity).toString();
            dao.deleteUnconfirmedActivityForUser(user, activity);
            if (operation.equals("ADD")) {
                dao.setActivityForUser(user, activity);
            } else {
                dao.deleteActivityForUser(user, activity);
            }
        }
    }

    public static void dontAcceptActivity(User user, Activity activity) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.deleteUnconfirmedActivityForUser(user, activity);
        }
    }

    public static List<Activity> getActivitiesList(){
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findAll();
        }
    }
    public static Activity getActivityByID(int id){
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findById(id);
        }
    }
    public static void addActivity(Activity activity) throws NotUniqueInsertionException {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            dao.create(activity);
        }
    }

    public static Category findCategoryById(int id) {
        try (CategoryDao dao = JDBCDaoFactory.getInstance().createCategoryDao()) {
            return dao.findById(id);
        }
    }

    public static void dontAcceptActivity(int id) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            dao.delete(id);
        }
    }

    public static List<Activity> getUnconfirmedActivitiesForUser(User user) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()){
            return dao.getUnconfirmedActivitiesForUser(user);
        }
    }

    public static void addCategory(String name) throws NotUniqueInsertionException {
        Category category = Category.createCategory(name);
        try (CategoryDao dao = JDBCDaoFactory.getInstance().createCategoryDao()) {
            dao.create(category);
        }
    }

    public static List<Category> getCategoriesList(){
        try (CategoryDao dao = JDBCDaoFactory.getInstance().createCategoryDao()) {
            return dao.findAll();
        }
    }

    public static void deleteCategory(int id) {
        try (CategoryDao dao = JDBCDaoFactory.getInstance().createCategoryDao()) {
            dao.delete(id);
        }
    }

    public static void deleteUser(int id){
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            dao.delete(id);
        }
    }

    public static List<User> getUsersList() {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findAll();
        }
    }

    public static User getUserByID(int id) {
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.findById(id);
        }
    }
    public static Operation getOperationForUserUnconfirmedActivity(User user, Activity activity){
        try (UserDao dao = JDBCDaoFactory.getInstance().createUserDao()) {
            return dao.getOperationForUserUnconfirmedActivity(user, activity);
        }
    }
}
