package com.example.controller.command.adminCommands.userManageCommand;

import com.example.controller.command.Command;
import com.example.controller.constants.Path;
import com.example.model.service.AdminService.userServices.UsersListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.setAttribute("usersList", UsersListService.getUsersList());
        return Path.MANAGE_USERS;
    }
}
