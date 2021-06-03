package com.example.controller.command.userCommands;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService;
import com.example.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddTime implements Command {
    private final Logger logger = LogManager.getLogger(AddTime.class);

    AdminService adminService;
    UserService userService;

    public AddTime(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        if (request.getParameter("time") == null || request.getParameter("time").equals("")){
            return "/app/userActivities";
        }

        User user = (User) request.getSession().getAttribute("user");
        int activity_id = Integer.parseInt(request.getParameter("activity_id"));
        Activity activity = adminService.getActivityByID(activity_id);
        String[] hoursAndMinutes = request.getParameter("time").split(":");

        int time = Integer.parseInt(hoursAndMinutes[0]) * 60 + Integer.parseInt(hoursAndMinutes[1]);

        userService.setSpentTime(user, activity, userService.getTimeByActivity(user, activity) + time);

        return "/app/userActivities";
    }
}
