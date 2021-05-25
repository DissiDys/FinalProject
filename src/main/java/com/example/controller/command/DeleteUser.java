package com.example.controller.command;

import com.example.controller.constants.Path;
import com.example.model.service.DeleteUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeleteUser implements Command{
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeleteUserService.deleteUser(id);
        return "/app/usersList";
    }
}
