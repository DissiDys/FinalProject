package com.example.controller.command.AdminCommands.categoriesCommand;

import com.example.controller.command.Command;
import com.example.model.service.categoryServices.DeleteCategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeleteCategory implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeleteCategoryService.deleteCategory(id);
        return "/app/activitiesCategories";
    }
}
