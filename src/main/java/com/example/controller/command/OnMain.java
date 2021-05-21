package com.example.controller.command;

import com.example.controller.constants.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class OnMain implements Command{
    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        return Path.MAIN_PAGE;
    }
}
