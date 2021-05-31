package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService.activityService.AcceptActivityService;
import com.example.model.service.AdminService.activityService.ActivitiesListService;
import com.example.model.service.AdminService.userServices.UsersListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AcceptActivityForUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int activity_id = Integer.parseInt(request.getParameter("activity_id"));

        User user = UsersListService.getUserByID(user_id);
        Activity activity = ActivitiesListService.getActivityByID(activity_id);

        if (request.getParameter("accept").equals("true")){
            AcceptActivityService.acceptActivity(user, activity);
        } else {
            AcceptActivityService.deleteActivity(user, activity);
        }

        return "/app/unconfirmedActivitiesList";
    }
}
