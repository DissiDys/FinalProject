package com.example.controller.command.userCommands;

import com.example.controller.command.Command;
import com.example.controller.util.ActivitiesUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SortUserActivities implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ActivitiesUtil.sort(request);
        ActivitiesUtil.filter(request);
        return "/app/userActivities";
    }
}
