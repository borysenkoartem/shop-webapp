package com.epam.borysenko.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.borysenko.constants.PathConstants.ADMIN_HOME_SERVLET;
import static com.epam.borysenko.constants.PathConstants.ADMIN_PAGE;


@WebServlet(urlPatterns = ADMIN_HOME_SERVLET)
public class AdminServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(ADMIN_PAGE,req,resp);
    }
}
