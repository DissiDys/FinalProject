package com.example.controller.filter;

import com.example.DB.entity.User;
import com.example.controller.Controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthFilter implements Filter {
    private final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Matcher m = Pattern.compile("(?<=role/)\\w*(?=/)").matcher(req.getRequestURI());
        String role = null;
        if (m.find()){
            role = m.group().toUpperCase(Locale.ROOT);
        }
        User.ROLE enumRole = (User.ROLE) req.getSession().getAttribute("role");

        if (role != null && (enumRole == null || !role.equals(enumRole.toString()))) {
            logger.error("Access denied, log as a " + role);
            req.setAttribute("msg", "Access error, auth, as a " + role);
            req.getRequestDispatcher("/view/jsp/error.jsp").forward(req, resp);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
