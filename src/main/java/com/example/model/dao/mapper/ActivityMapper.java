package com.example.model.dao.mapper;

import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements ObjectMapper<Activity>{
    @Override
    public Activity extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = JDBCDaoFactory.getInstance().createCategoryDao().findById(rs.getInt("category_id"));
        Activity activity = Activity.createActivity(rs.getString("name"), category);
        return activity;
    }
}
