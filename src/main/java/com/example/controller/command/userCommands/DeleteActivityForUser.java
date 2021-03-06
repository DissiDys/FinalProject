package com.example.controller.command.userCommands;

import com.example.controller.command.Command;
import com.example.model.entity.Activity;
import com.example.model.entity.User;
import com.example.model.entity.enums.Operation;
import com.example.model.service.AdminService;
import com.example.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeleteActivityForUser implements Command {
    AdminService adminService;
    UserService userService;

    public DeleteActivityForUser(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Activity activity = adminService.getActivityByID(Integer.parseInt(request.getParameter("activity_id")));
        userService.sendRequest(user, activity, Operation.DELETE);
        request.setAttribute("msg".concat(activity.getName()), true);
        return "redirect:/app/userActivities";
    }
}
