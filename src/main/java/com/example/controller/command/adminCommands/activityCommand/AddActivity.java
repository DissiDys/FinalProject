package com.example.controller.command.adminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.entity.Activity;
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
        Activity activity = Activity.createActivity(request.getParameter("name"), AdminService.findCategoryById(Integer.parseInt(request.getParameter("category"))));
        try {
            AdminService.addActivity(activity);
        } catch (NotUniqueInsertionException e) {
            logger.error("Those activity already exist, name: " + activity.getName());
        }
        return "/app/activities";
    }
}
