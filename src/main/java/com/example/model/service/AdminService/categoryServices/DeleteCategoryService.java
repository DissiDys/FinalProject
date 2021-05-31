package com.example.model.service.AdminService.categoryServices;

import com.example.model.dao.CategoryDao;
import com.example.model.dao.impl.JDBCDaoFactory;

public class DeleteCategoryService {
    public static void deleteCategory(int id) {
        try (CategoryDao dao = JDBCDaoFactory.getInstance().createCategoryDao()) {
            dao.delete(id);
        }
    }
}
