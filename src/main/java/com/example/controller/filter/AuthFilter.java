package com.example.controller.filter;

import com.example.DB.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Matcher m = Pattern.compile("\\w*(?=Account)").matcher(req.getRequestURI());
        String role = null;
        if (m.find()){
            role = m.group();
        }
        User.ROLE enumRole = (User.ROLE) req.getSession().getAttribute("role");

        if (role != null && (enumRole == null || !role.equals(User.ROLE.getStringValue(enumRole)))) {
            req.setAttribute("msg", "Access error, auth, as a " + role);
            req.getRequestDispatcher("/view/error.jsp").forward(req, resp);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
