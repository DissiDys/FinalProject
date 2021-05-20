package com.example.controller.command;

import com.example.DB.entity.User;
import com.example.controller.constants.Path;
import com.example.service.LoginService;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println(login + " " + password);
        if( login == null || login.equals("") || password == null || password.equals("")  ){
            return Path.LOGIN;
        }

        request.getSession().setAttribute("userLogin", login);

        if(CommandUtility.checkUserIsLogged(request, login)){
            request.setAttribute("user_already_logged", true);
            return Path.LOGIN;
        }

        if (LoginService.DBContainsUser(login, password)) {
            if (login.equals("Admin")){
                CommandUtility.setUserRole(request, User.ROLE.ADMIN);
                return "/view/role/adminAccount.jsp";
            } else {
                CommandUtility.setUserRole(request, User.ROLE.USER);
                return "/view/role/userAccount.jsp";
            }
        }

        request.setAttribute("wrong_data", true);
        return "/view/login.jsp";
    }
}
