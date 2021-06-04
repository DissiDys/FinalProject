package com.example.controller.command.adminCommands.userManageCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService;
import com.example.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class UserPageForAdmin implements Command {
    AdminService adminService;
    UserService userService;

    public UserPageForAdmin(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        if (request.getParameter("user_id") == null || request.getParameter("user_id").equals("")) {
            return "/app/usersList";
        }

        int userID = Integer.parseInt(request.getParameter("user_id"));
        User user = adminService.getUserByID(userID);
        List<Activity> activitiesList = userService.getUserActivitiesList(user);
        request.setAttribute("activitiesList", activitiesList);

        for (Activity activity : activitiesList) {
            int minutes = userService.getTimeByActivity(user, activity);
            int hours = minutes / 60;
            minutes = minutes % 60;
            request.setAttribute("hoursSpent".concat(activity.getName()), hours);
            request.setAttribute("minutesSpent".concat(activity.getName()), minutes);
        }

        return "/view/jsp/role/admin/userPageForAdmin.jsp";
    }
}
