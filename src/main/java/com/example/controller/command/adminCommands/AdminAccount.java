package com.example.controller.command.adminCommands;

import com.example.controller.command.Command;
import com.example.controller.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminAccount implements Command {
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        return Path.ADMIN_PAGE;
    }
}
