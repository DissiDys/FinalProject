package com.example.controller.command.adminCommands.userManageCommand;

import com.example.controller.command.Command;
import com.example.controller.constants.Path;
import com.example.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserList implements Command {
    AdminService adminService;

    public UserList(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.setAttribute("usersList", adminService.getUsersList());
        return Path.MANAGE_USERS;
    }
}
