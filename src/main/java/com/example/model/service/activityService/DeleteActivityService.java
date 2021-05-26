package com.example.model.service.activityService;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.impl.JDBCActivityDao;
import com.example.model.dao.impl.JDBCCategoryDao;
import com.example.model.dao.impl.JDBCDaoFactory;

public class DeleteActivityService {
    public static void deleteActivity(int id) {
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            dao.delete(id);
        }
    }
}
