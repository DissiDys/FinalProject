package com.example.controller.command.AdminCommands.activityCommand;

import com.example.controller.command.Command;
import com.example.model.service.activityService.DeleteActivityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeleteActivity implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeleteActivityService.deleteActivity(id);
        return "/app/activities";
    }
}
