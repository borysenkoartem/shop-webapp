package com.epam.borysenko.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.STATUS_CODE;
import static com.epam.borysenko.constants.PathConstants.ERROR_PAGE;

@WebServlet(urlPatterns = "/error")

public class ErrorServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(STATUS_CODE, req.getSession().getAttribute(STATUS_CODE));
        req.getSession().removeAttribute(STATUS_CODE);
        forwardToPage(ERROR_PAGE, req, resp);
    }
}
