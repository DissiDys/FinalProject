package com.example.model.service.activityService;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.CategoryDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;

public class AddActivityService {
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
}
