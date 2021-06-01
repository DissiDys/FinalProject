package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AcceptActivityForUser implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int activity_id = Integer.parseInt(request.getParameter("activity_id"));

        User user = AdminService.getUserByID(user_id);
        Activity activity = AdminService.getActivityByID(activity_id);

        if (request.getParameter("accept").equals("true")){
            AdminService.acceptActivity(user, activity);
        } else {
            AdminService.dontAcceptActivity(user, activity);
        }

        return "/app/unconfirmedActivitiesList";
    }
}
