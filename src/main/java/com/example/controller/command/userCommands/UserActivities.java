package com.example.controller.command.userCommands;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.service.AdminService;
import com.example.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class UserActivities implements Command {
    AdminService adminService;
    UserService userService;

    public UserActivities(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Category> categoriesList = adminService.getCategoriesList();
        List<Activity> activitiesList = adminService.getActivitiesList();
        if (request.getAttribute("activitiesList") == null) {
            List<Activity> userActivitiesList = userService.getUserActivitiesList(user);
            request.setAttribute("activitiesList", userActivitiesList);
        }
        for (Activity activity : (List<Activity>) request.getAttribute("activitiesList")) {
            int minutes = userService.getTimeByActivity(user, activity);
            int hours = minutes / 60;
            minutes = minutes % 60;
            request.setAttribute("hoursSpent".concat(activity.getName()), hours);
            request.setAttribute("minutesSpent".concat(activity.getName()), minutes);
        }
        request.setAttribute("allActivitiesList", activitiesList);
        request.setAttribute("categoriesList", categoriesList);
        return "/view/jsp/role/user/userAccount.jsp";
    }
}
