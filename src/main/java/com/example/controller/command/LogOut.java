package com.example.controller.command;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.deleteUserFromLogged(request);
        CommandUtility.deleteUserRole(request);
        return Path.LOGOUT;
    }
}
