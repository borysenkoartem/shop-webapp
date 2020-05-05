package com.epam.borysenko.servlet.page;

import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.service.UserService;
import com.epam.borysenko.servlet.AbstractServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.*;
import static com.epam.borysenko.constants.PathConstants.*;
import static com.epam.borysenko.constants.RegistrationFormConstants.LOGIN;
import static com.epam.borysenko.constants.RegistrationFormConstants.PASSWORD;
import static com.epam.borysenko.constants.ValidationConstants.LOGIN_ERROR;
import static com.epam.borysenko.constants.ValidationConstants.LOGIN_ERROR_VALIDATION_TEXT;

@WebServlet(urlPatterns = LOGIN_SERVLET)
public class LoginServlet extends AbstractServlet {

    private static final long serialVersionUID = 1633572282207026978L;
    private static final Logger LOGIN_LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(CURRENT_USER) == null) {
            req.setAttribute(AUTHORISATION_ERROR, req.getSession().getAttribute(AUTHORISATION_ERROR));
            req.getSession().removeAttribute(AUTHORISATION_ERROR);
            req.setAttribute(LOGIN_ERROR, req.getSession().getAttribute(LOGIN_ERROR));
            req.getSession().removeAttribute(LOGIN_ERROR);
            forwardToPage(LOGIN_PAGE, req, resp);
        } else {
            resp.sendRedirect(HOME_SERVLET);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute(USER_SERVICE);
        User currentUser = null;
        try {
            currentUser = userService.getAccountByLogin(req.getParameter(LOGIN));
        } catch (ServiceException e) {
            LOGIN_LOGGER.error("during getting account from DB");
        }
        if (currentUser != null) {
            if (currentUser.getPassword().equals(req.getParameter(PASSWORD))) {
                req.getSession().setAttribute(CURRENT_USER, currentUser);
                if (req.getSession().getAttribute(SUCCESS_REDIRECT_URL_AFTER_SIGN_IN) == null) {
                    resp.sendRedirect(PRODUCTS_SERVLET);
                } else {
                    String url = (String) req.getSession().getAttribute(SUCCESS_REDIRECT_URL_AFTER_SIGN_IN);
                    req.getSession().removeAttribute(SUCCESS_REDIRECT_URL_AFTER_SIGN_IN);
                    resp.sendRedirect(url);
                }
            }else {
                req.getSession().setAttribute(LOGIN_ERROR, LOGIN_ERROR_VALIDATION_TEXT);
            }
        } else {
            req.getSession().setAttribute(LOGIN_ERROR, LOGIN_ERROR_VALIDATION_TEXT);
            resp.sendRedirect(LOGIN_SERVLET);
        }
    }
}

