package com.example.controller.listener;

import com.example.DB.entity.User;
import com.example.controller.command.CommandUtility;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        CommandUtility.setUserRole(se.getSession(), User.ROLE.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        CommandUtility.deleteUserFromLogged(se.getSession());
    }
}
