package com.example.controller.util;

import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.entity.User;
import com.example.model.service.AdminService.activityService.ActivitiesListService;
import com.example.model.service.AdminService.categoryServices.CategoriesListService;
import com.example.model.service.UserService.UserActivitiesListService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitiesUtil {
    public static void sort(HttpServletRequest request, List<Activity> sortedActivityList) {
        String sortingMethod = request.getParameter("sort");

        switch (sortingMethod) {
            case "name":
                sortedActivityList.sort(Comparator.comparing(Activity::getName));
                break;
            case "category":
                sortedActivityList.sort(Comparator.comparing(o -> o.getCategory().getName()));
                break;
            case "user":
                //TODO sorting by users
                break;
        }

        request.setAttribute("activitiesList", sortedActivityList);
    }

    public static void filter(HttpServletRequest request) {
        User.ROLE role = (User.ROLE) request.getSession().getAttribute("role");
        List<Activity> activityList = new ArrayList<>();
        if (role.name().equals("ADMIN")) {
            activityList = ActivitiesListService.getActivitiesList();
        }
        if (role.name().equals("USER")){
            User user = (User) request.getSession().getAttribute("user");
            activityList = UserActivitiesListService.getUserActivitiesList(user);
        }

        for (Category category : CategoriesListService.getCategoriesList()) {
           if (request.getParameter(category.getName()) == null){
               activityList = activityList.stream()
                       .filter(activity -> !activity.getCategory().getName().equals(category.getName()))
                       .collect(Collectors.toList());
           }
        }

        request.setAttribute("activitiesList", activityList);
    }
}
