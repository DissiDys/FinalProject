package com.example.controller.command;

import com.example.controller.constants.Path;
import com.example.controller.util.Validation;
import com.example.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;

public class Reg implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login + " " + password);
        if(login == null || login.equals("") || password == null || password.equals("")
           || !Validation.isLoginValid(login) || !Validation.isPasswordValid(password)){
            return Path.REGISTRATION;
        }
        if (RegistrationService.regNewUser(login, password)) {
            request.setAttribute("success_reg",true);
            return Path.LOGIN;
        }

        request.setAttribute("not_unique_login", true);
        return Path.REGISTRATION;
    }
}
