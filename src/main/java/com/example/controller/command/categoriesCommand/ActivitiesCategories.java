package com.example.controller.command.categoriesCommand;

import com.example.controller.command.Command;
import com.example.model.service.categoryServices.CategoriesListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ActivitiesCategories implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.setAttribute("categoriesList", CategoriesListService.getCategoriesList());
        return "/view/jsp/role/admin/activitiesCategories.jsp";
    }
}
