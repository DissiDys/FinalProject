package com.example.model.dao.mapper;

import com.example.model.entity.Activity;
import com.example.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements ObjectMapper<Activity> {
    @Override
    public Activity extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = Category.createCategory(rs.getString("category.name"));
        category.setId(rs.getInt("category.id"));
        Activity activity = Activity.createActivity(rs.getString("activity.name"), category);
        activity.setId(rs.getInt("activity.id"));
        return activity;
    }
}
