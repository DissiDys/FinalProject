package com.example.model.service.AdminService.categoryServices;

import com.example.model.dao.CategoryDao;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Category;

public class AddCategoryService {
    public static void addCategory(String name) throws NotUniqueInsertionException {
        Category category = Category.createCategory(name);
        try (CategoryDao dao = JDBCDaoFactory.getInstance().createCategoryDao()) {
            dao.create(category);
        }
    }
}
