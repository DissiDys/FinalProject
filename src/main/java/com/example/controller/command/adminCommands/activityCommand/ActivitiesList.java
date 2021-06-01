package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.service.AdminService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ActivitiesList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        if (request.getAttribute("activitiesList") == null) {
            request.setAttribute("activitiesList", AdminService.getActivitiesList());
        }
        request.setAttribute("categoriesList", AdminService.getCategoriesList());
        return "/view/jsp/role/admin/manageActivities.jsp";
    }
}
