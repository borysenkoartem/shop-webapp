package com.epam.borysenko.servlet.page;

import com.epam.borysenko.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.CURRENT_USER;
import static com.epam.borysenko.constants.PathConstants.LOGIN_SERVLET;
import static com.epam.borysenko.constants.PathConstants.LOGOUT_SERVLET;

@WebServlet(urlPatterns = LOGOUT_SERVLET)
public class LogoutServlet extends AbstractServlet {

    private static final long serialVersionUID = 3541158705111056475L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute(CURRENT_USER);
        resp.sendRedirect(LOGIN_SERVLET);
    }
}
