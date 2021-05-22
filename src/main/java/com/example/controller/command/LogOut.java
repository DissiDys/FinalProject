package com.example.controller.command;

import com.example.controller.constants.Path;
import com.example.model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command{
    private final Logger logger = LogManager.getLogger(LogOut.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) (request.getSession().getAttribute("user"));
        logger.info("User with login " + user.getLogin() + " logged out");
        CommandUtility.deleteUserFromLogged(request.getSession());
        CommandUtility.deleteUserRole(request.getSession());
        return Path.MAIN_PAGE;
    }
}
