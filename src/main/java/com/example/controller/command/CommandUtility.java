package com.example.controller.command;

import com.example.DB.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            User.ROLE role) {
        HttpSession session = request.getSession();
        session.setAttribute("role", role);
    }
    static void deleteUserRole(HttpServletRequest request){
        request.getSession().removeAttribute("role");
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null){
            loggedUsers = new HashSet<>();
        }
        if(loggedUsers.stream().anyMatch(userName::equals)){
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
    static void deleteUserFromLogged(HttpServletRequest request){
        String userLogin = (String)request.getSession().getAttribute("userLogin");
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null){
            loggedUsers = new HashSet<>();
        }
        loggedUsers.removeIf(uL -> uL.equals(userLogin));
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }
}
