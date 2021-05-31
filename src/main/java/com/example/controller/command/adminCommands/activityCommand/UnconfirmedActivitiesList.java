package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService.activityService.UnconfirmedActivitiesListService;
import com.example.model.service.AdminService.userServices.UsersListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnconfirmedActivitiesList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        List<User> usersList = new ArrayList<>();

        for (User user: UsersListService.getUsersList()) {
            List<Activity> activitiesList = UnconfirmedActivitiesListService.getUnconfirmedActivitiesForUser(user);
            if (activitiesList.size() > 0){
                usersList.add(user);
                request.setAttribute("activitiesList" + user.getLogin(), activitiesList);
            }
        }

        request.setAttribute("usersList", usersList);
        return "/view/jsp/role/admin/acceptActivities.jsp";
    }
}
