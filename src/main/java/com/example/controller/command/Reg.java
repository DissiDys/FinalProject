package com.example.controller.command;

import com.example.controller.constants.Path;
import com.example.controller.util.ValidationUtil;
import com.example.model.dao.exception.NotUniqueInsertionException;
import com.example.model.service.GuestService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Reg implements Command {
    private final Logger logger = LogManager.getLogger(Reg.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login + " " + password);
        if (login == null || login.equals("") || password == null || password.equals("")
                || !ValidationUtil.isLoginValid(login) || !ValidationUtil.isPasswordValid(password)) {
            logger.error("Invalid login or password ( login: " + login + ", password: " + password + " )");
            return Path.REGISTRATION;
        }
        try {
            GuestService.regNewUser(login, password);
        } catch (NotUniqueInsertionException e) {
            logger.info("Not unique login ( login: " + login + " )");
            request.setAttribute("not_unique_login", true);
            return Path.REGISTRATION;
        }
        request.setAttribute("success_reg", true);
        logger.info("Success registration ( login: " + login + ", password: " + password + " )");
        return Path.LOGIN;
    }
}
