package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.service.AdminService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class ActivitiesList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        if (request.getAttribute("activitiesList") == null) {
            request.setAttribute("activitiesList", AdminService.getActivitiesList());
        }
        for (Activity activity : (List<Activity>) request.getAttribute("activitiesList")) {
            request.setAttribute("amountOfUsers" + activity.getName(), AdminService.getAmountOfUsersOnActivity(activity));
        }
        request.setAttribute("categoriesList", AdminService.getCategoriesList());
        return "/view/jsp/role/admin/manageActivities.jsp";
    }
}
