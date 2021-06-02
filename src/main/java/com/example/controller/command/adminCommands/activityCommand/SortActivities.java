package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.controller.util.ActivitiesUtil;
import com.example.model.entity.Activity;
import com.example.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class SortActivities implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) request.getSession().getAttribute("role");
        ActivitiesUtil.filter(request);
        ActivitiesUtil.sort(request);
        if (role.name().equals("ADMIN")) {
            return "/app/activities";
        }
        return "/app/userActivities";
    }
}
