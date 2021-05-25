package com.example.controller;

import com.example.controller.command.*;
import com.example.controller.constants.Path;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller extends HttpServlet {
    private final Logger logger = LogManager.getLogger(Controller.class);

    private Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Reg());
        commands.put("usersList", new ManageUsers());
        commands.put("deleteUser", new DeleteUser());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        logger.debug("User`s request URL: " + request.getRequestURL());

        path = path.replaceAll(".*/app/", "");

        Command command = commands.getOrDefault(path,
                (r) -> Path.MAIN_PAGE);
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            logger.debug("Redirection on " + page);
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            logger.debug("Opening " + page);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
