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
import java.sql.Time;
import java.util.List;

public class UserActivities implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Category> categoriesList = AdminService.getCategoriesList();
        List<Activity> activitiesList = AdminService.getActivitiesList();
        if (request.getAttribute("activitiesList") == null) {
            List<Activity> userActivitiesList = UserService.getUserActivitiesList(user);
            request.setAttribute("activitiesList", userActivitiesList);
        }
        for (Activity activity : activitiesList) {
            Time time = UserService.getTimeByActivity(user, activity);
            if (time != null) {
                request.setAttribute("timeSpent".concat(activity.getName()), time.toString().substring(0, 5));
            }
        }
        request.setAttribute("allActivitiesList", activitiesList);
        request.setAttribute("categoriesList", categoriesList);
        return "/view/jsp/role/user/userAccount.jsp";
    }
}
