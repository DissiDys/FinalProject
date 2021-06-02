package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.entity.Activity;
import com.example.model.entity.Category;
import com.example.model.service.AdminService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddActivity implements Command {
    private final Logger logger = LogManager.getLogger(AddActivity.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        if (request.getParameter("category_id") == null || request.getParameter("category_id").equals("")
                || request.getParameter("name") == null || request.getParameter("name").equals("")) {
            return "/app/activities";
        }
        Category category = AdminService.findCategoryById(Integer.parseInt(request.getParameter("category_id")));
        Activity activity = Activity.createActivity(request.getParameter("name"), category);
        try {
            AdminService.addActivity(activity);
        } catch (NotUniqueInsertionException e) {
            logger.error("Those activity already exist, name: " + activity.getName());
        }
        return "/app/activities";
    }
}
