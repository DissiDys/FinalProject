package com.example.controller.command.activityCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.activityService.ActivitiesListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortActivities implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        String sortingMethod = request.getParameter("sort");
        List<Activity> sortedActivityList = ActivitiesListService.getActivitiesList();

        switch (sortingMethod){
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
        return "/app/activities";
    }
}
