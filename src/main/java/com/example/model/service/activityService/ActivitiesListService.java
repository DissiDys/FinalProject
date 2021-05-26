package com.example.model.service.activityService;

import com.example.model.dao.ActivityDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;

import java.util.List;

public class ActivitiesListService {
    public static List<Activity> getActivitiesList(){
        try (ActivityDao dao = JDBCDaoFactory.getInstance().createActivityDao()) {
            return dao.findAll();
        }
    }
}
