package com.example.controller.command.userCommands;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;
import com.example.model.service.AdminService;
import com.example.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SendRequestToAddActivity implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        if (request.getParameter("activity_id") == null || request.getParameter("activity_id").equals("")) {
            return "/app/userActivities";
        }
        User user = (User) request.getSession().getAttribute("user");
        Activity activity = AdminService.getActivityByID(Integer.parseInt(request.getParameter("activity_id")));

        UserService.sendRequest(user, activity, Operation.ADD);
        return "/app/userActivities";
    }
}
