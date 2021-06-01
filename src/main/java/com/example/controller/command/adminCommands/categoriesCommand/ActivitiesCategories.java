package com.example.controller.command.adminCommands.categoriesCommand;

import com.example.controller.command.Command;
import com.example.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ActivitiesCategories implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.setAttribute("categoriesList", AdminService.getCategoriesList());
        return "/view/jsp/role/admin/activitiesCategories.jsp";
    }
}
