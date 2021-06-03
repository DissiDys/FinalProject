package com.example.controller.util;

import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.service.AdminService;
import com.example.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitiesUtil {
    AdminService adminService;
    UserService userService;

    public ActivitiesUtil(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    public void sort(HttpServletRequest request) {
        String sortingMethod = request.getParameter("sort");
        List<Activity> sortedActivityList = (List<Activity>) request.getAttribute("activitiesList");
        switch (sortingMethod) {
            case "name":
                sortedActivityList.sort(Comparator.comparing(Activity::getName));
                break;
            case "category":
                sortedActivityList.sort(Comparator.comparing(o -> o.getCategory().getName()));
                break;
            case "users":
                sortedActivityList.sort(Comparator.comparing(o -> new AdminService().getAmountOfUsersOnActivity(o)));
                break;
        }

        request.setAttribute("activitiesList", sortedActivityList);
    }

    public void filter(HttpServletRequest request) {
        User.ROLE role = (User.ROLE) request.getSession().getAttribute("role");
        List<Activity> activityList = new ArrayList<>();
        if (role.name().equals("ADMIN")) {
            activityList = adminService.getActivitiesList();
        }
        if (role.name().equals("USER")){
            User user = (User) request.getSession().getAttribute("user");
            activityList = userService.getUserActivitiesList(user);
        }

        for (Category category : adminService.getCategoriesList()) {
           if (request.getParameter(category.getName()) == null){
               activityList = activityList.stream()
                       .filter(activity -> !activity.getCategory().getName().equals(category.getName()))
                       .collect(Collectors.toList());
           }
        }

        request.setAttribute("activitiesList", activityList);
    }
}
