package com.example.controller.command;

import com.example.model.entity.User;
import com.example.controller.constants.Path;
import com.example.model.service.GuestService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    private final Logger logger = LogManager.getLogger(Login.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        if (login == null || login.equals("") || password == null || password.equals("")) {
            logger.error("Empty login or password ( login: " + login + ", password: " + password + " )");
            return Path.LOGIN;
        }

        User user = User.createUser(login, password);
        if (GuestService.DBContainsUser(user)) {
            if (CommandUtility.checkUserIsLogged(request.getSession(), user)) {
                request.setAttribute("user_already_logged", true);
                logger.error("User with login " + login + " already logged");
                return Path.LOGIN;
            }
            request.getSession().setAttribute("user", user);

            if (login.equals("Admin")) {
                CommandUtility.setUserRole(request.getSession(), User.ROLE.ADMIN);
                logger.info("Success login, as Admin ( login: " + login + " )");
                return Path.ADMIN_PAGE;
            } else {
                CommandUtility.setUserRole(request.getSession(), User.ROLE.USER);
                logger.info("Success login, as User ( login: " + login + " )");
                return "/app/userActivities";
            }
        }

        logger.error("Incorrect login or password ( login: " + login + ", password: " + password + " )");
        request.setAttribute("wrong_data", true);
        return Path.LOGIN;
    }
}
