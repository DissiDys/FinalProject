package com.example.model.dao.mapper;

import com.example.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements ObjectMapper {
    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = Category.createCategory(rs.getString("name"));
        category.setId(rs.getLong(1));
        return category;
    }
}
