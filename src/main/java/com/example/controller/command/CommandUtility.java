package com.example.controller.command;

import com.example.DB.entity.User;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    public static void setUserRole(HttpSession session,
                            User.ROLE role) {
        session.setAttribute("role", role);
    }
    public static void deleteUserRole(HttpSession session){
        session.removeAttribute("role");
    }

    public static boolean checkUserIsLogged(HttpSession session, String userLogin){
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null){
            loggedUsers = new HashSet<>();
        }
        if(loggedUsers.stream().anyMatch(userLogin::equals)){
            return true;
        }
        loggedUsers.add(userLogin);
        session.getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
    public static void deleteUserFromLogged(HttpSession session){
        String userLogin = (String)session.getAttribute("userLogin");
        HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext().getAttribute("loggedUsers");
        if (loggedUsers == null){
            loggedUsers = new HashSet<>();
        }
        loggedUsers.removeIf(uL -> uL.equals(userLogin));
        session.getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }
}
