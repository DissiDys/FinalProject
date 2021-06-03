package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnconfirmedActivitiesList implements Command {
    AdminService adminService;

    public UnconfirmedActivitiesList(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        List<User> usersList = new ArrayList<>();

        for (User user : adminService.getUsersList()) {
            List<Activity> activitiesList = adminService.getUnconfirmedActivitiesForUser(user);
            if (activitiesList.size() > 0) {
                usersList.add(user);
                request.setAttribute("activitiesList" + user.getLogin(), activitiesList);
                for (Activity activity : activitiesList) {
                    String operation = adminService.getOperationForUserUnconfirmedActivity(user, activity).toString();
                    request.setAttribute("operation" + user.getLogin() + activity.getName(), operation);
                }
            }
        }

        request.setAttribute("usersList", usersList);
        return "/view/jsp/role/admin/acceptActivities.jsp";
    }
}
