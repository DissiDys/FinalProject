package com.example.controller.command.AdminCommands.userCommand;

import com.example.controller.command.Command;
import com.example.controller.constants.Path;
import com.example.model.service.userServices.UsersListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UsersList implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.setAttribute("usersList", UsersListService.getUsersList());
        return Path.MANAGE_USERS;
    }
}
