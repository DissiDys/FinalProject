package com.example.controller.command.userCommands;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService.activityService.ActivitiesListService;
import com.example.model.service.UserService.SendRequestToAddActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SendRequestToAddActivity implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Activity activity = ActivitiesListService.getActivityByID(Integer.parseInt(request.getParameter("activity")));

        SendRequestToAddActivityService.sendRequest(user, activity);
        return "/app/userActivities";
    }
}
