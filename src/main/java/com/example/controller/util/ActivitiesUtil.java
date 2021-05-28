package com.example.controller.util;

import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.service.activityService.ActivitiesListService;
import com.example.model.service.categoryServices.CategoriesListService;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitiesUtil {
    public static void sort(HttpServletRequest request) {
        String sortingMethod = request.getParameter("sort");
        List<Activity> sortedActivityList = ActivitiesListService.getActivitiesList();

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
        List<Activity> activityList = ActivitiesListService.getActivitiesList();

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
