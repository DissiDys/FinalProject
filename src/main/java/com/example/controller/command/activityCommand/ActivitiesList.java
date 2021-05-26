package com.example.controller.command.activityCommand;

import com.example.controller.command.Command;
import com.example.model.service.activityService.ActivitiesListService;
import com.example.model.service.categoryServices.CategoriesListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ActivitiesList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        if (request.getAttribute("activitiesList") == null) {
            request.setAttribute("activitiesList", ActivitiesListService.getActivitiesList());
        }
        request.setAttribute("categoriesList", CategoriesListService.getCategoriesList());
        return "/view/jsp/role/admin/manageActivities.jsp";
    }
}
