package com.example.controller.command.userCommands;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.service.AdminService;
import com.example.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTime implements Command {
    private final Logger logger = LogManager.getLogger(AddTime.class);
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        SimpleDateFormat format= new SimpleDateFormat("hh:mm:ss");
        try {
            User user = (User) request.getSession().getAttribute("user");
            int id = Integer.parseInt(request.getParameter("activity_id"));
            Activity activity = AdminService.getActivityByID(id);
            Time tempTime = new Time(format.parse(UserService.getTimeByActivity(user, activity) + ":00").getTime());
            Time time = new Time(format.parse(request.getParameter("time") + ":00").getTime() + tempTime.getTime() + 10800000);
            UserService.setSpentTime(user, activity, time);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return "/app/userActivities";
    }
}
