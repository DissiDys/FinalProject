package com.example.controller.command.adminCommands.userManageCommand;

import com.example.controller.command.Command;
import com.example.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeleteUser implements Command {
    AdminService adminService;

    public DeleteUser(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("user_id"));
        adminService.deleteUser(id);
        return "redirect:/app/usersList";
    }
}
