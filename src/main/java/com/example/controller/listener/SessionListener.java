package com.example.controller.listener;

import com.example.controller.command.CommandUtility;
import com.example.model.entity.User;

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
