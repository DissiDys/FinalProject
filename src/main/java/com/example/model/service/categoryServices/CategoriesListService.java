package com.example.model.service.categoryServices;

import com.example.model.dao.impl.JDBCDaoFactory;
import com.example.model.entity.Category;

import java.util.List;

public class CategoriesListService {
    public static List<Category> getCategoriesList(){
        return JDBCDaoFactory.getInstance().createCategoryDao().findAll();
    }
}
