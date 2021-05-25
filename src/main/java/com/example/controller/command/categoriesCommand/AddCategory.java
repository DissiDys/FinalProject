package com.example.controller.command.categoriesCommand;

import com.example.controller.command.Command;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.service.categoryServices.AddCategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddCategory implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String name = request.getParameter("name");
        try {
            AddCategoryService.addCategory(name);
        } catch (NotUniqueInsertionException e) {
            System.out.println(e.getMessage());
        }
        return "/app/activitiesCategories";
    }
}
