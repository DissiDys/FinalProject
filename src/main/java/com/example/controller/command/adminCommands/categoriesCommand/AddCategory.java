package com.example.controller.command.adminCommands.categoriesCommand;

import com.example.controller.command.Command;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddCategory implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null || name.equals("")) {
            return "/app/activitiesCategories";
        }
        try {
            AdminService.addCategory(name);
        } catch (NotUniqueInsertionException e) {
            System.out.println(e.getMessage());
        }

        return "/app/activitiesCategories";
    }
}
