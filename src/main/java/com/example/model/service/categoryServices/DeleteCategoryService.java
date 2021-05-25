package com.example.model.service.categoryServices;

import com.example.model.dao.impl.JDBCDaoFactory;

public class DeleteCategoryService {
    public static void deleteCategory(int id) {
        JDBCDaoFactory.getInstance().createCategoryDao().delete(id);
    }
}
