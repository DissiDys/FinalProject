package com.example.model.service.categoryServices;

import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Category;

public class AddCategoryService {
    public static void addCategory(String name) throws NotUniqueInsertionException {
        Category category = Category.createCategory(name);
        JDBCDaoFactory.getInstance().createCategoryDao().create(category);
    }
}
