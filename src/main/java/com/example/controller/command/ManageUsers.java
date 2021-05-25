package com.example.controller.command;

import com.example.controller.constants.Path;
import com.example.model.service.UsersListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManageUsers implements Command{
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        request.setAttribute("usersList", UsersListService.getUsersList());
        return Path.MANAGE_USERS;
    }
}
