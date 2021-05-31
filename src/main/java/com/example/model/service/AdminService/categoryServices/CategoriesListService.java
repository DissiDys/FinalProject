package com.example.model.service.AdminService.categoryServices;

import com.example.model.dao.CategoryDao;
import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Category;

import java.util.List;

public class CategoriesListService {
    public static List<Category> getCategoriesList(){
        try (CategoryDao dao = JDBCDaoFactory.getInstance().createCategoryDao()) {
            return dao.findAll();
        }
    }
}
