package com.example.controller;

import com.example.controller.command.Command;
import com.example.controller.command.LogOut;
import com.example.controller.command.Login;
import com.example.controller.command.Reg;
import com.example.controller.command.adminCommands.AdminAccount;
import com.example.controller.command.adminCommands.activityCommand.*;
import com.example.controller.command.adminCommands.categoriesCommand.ActivitiesCategories;
import com.example.controller.command.adminCommands.categoriesCommand.AddCategory;
import com.example.controller.command.adminCommands.categoriesCommand.DeleteCategory;
import com.example.controller.command.adminCommands.userManageCommand.DeleteUser;
import com.example.controller.command.adminCommands.userManageCommand.UserList;
import com.example.controller.command.adminCommands.userManageCommand.UserPageForAdmin;
import com.example.controller.command.userCommands.AddActivityForUser;
import com.example.controller.command.userCommands.AddTime;
import com.example.controller.command.userCommands.DeleteActivityForUser;
import com.example.controller.command.userCommands.UserActivities;
import com.example.model.service.AdminService;
import com.example.model.service.GuestService;
import com.example.model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller extends HttpServlet {
    private final Logger logger = LogManager.getLogger(Controller.class);
    private Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put("logout", new LogOut());
        commands.put("login", new Login(new GuestService()));
        commands.put("registration", new Reg(new GuestService()));
        commands.put("adminAccount", new AdminAccount());
        commands.put("usersList", new UserList(new AdminService()));
        commands.put("deleteUser", new DeleteUser(new AdminService()));
        commands.put("activitiesCategories", new ActivitiesCategories(new AdminService()));
        commands.put("deleteCategory", new DeleteCategory(new AdminService()));
        commands.put("addCategory", new AddCategory(new AdminService()));
        commands.put("activities", new ActivitiesList(new AdminService()));
        commands.put("deleteActivity", new DeleteActivity(new AdminService()));
        commands.put("addActivity", new AddActivity(new AdminService()));
        commands.put("sortActivities", new SortActivities());
        commands.put("userActivities", new UserActivities(new AdminService(), new UserService()));
        commands.put("addTime", new AddTime(new AdminService(), new UserService()));
        commands.put("sendAddActivityRequestToAdmin", new AddActivityForUser(new AdminService(), new UserService()));
        commands.put("acceptActivityForUser", new AcceptActivityForUser(new AdminService()));
        commands.put("unconfirmedActivitiesList", new UnconfirmedActivitiesList(new AdminService()));
        commands.put("deleteUserActivity", new DeleteActivityForUser(new AdminService(), new UserService()));
        commands.put("userPageForAdmin", new UserPageForAdmin(new AdminService(), new UserService()));
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
                (r) -> "/app/logout");
        String page = command.execute(request);

        if (page.contains("redirect:")) {
            logger.debug("Redirection on " + page);
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            logger.debug("Opening " + page);
            request.getRequestDispatcher(page).forward(request, response);
        }
        logger.info("Role: " + request.getSession().getAttribute("role"));
    }
}
