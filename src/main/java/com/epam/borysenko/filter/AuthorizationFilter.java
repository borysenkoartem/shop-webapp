package com.epam.borysenko.filter;

import com.epam.borysenko.entity.User;
import com.epam.borysenko.factory.impl.SecurityFactoryImpl;
import com.epam.borysenko.model.Constraint;
import com.epam.borysenko.model.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.epam.borysenko.constants.ContextConstants.*;
import static com.epam.borysenko.constants.PathConstants.ERROR_SERVLET;
import static com.epam.borysenko.constants.PathConstants.LOGIN_SERVLET;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {

    private static final Logger AUTHORIZATION_FILTER_LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    private Security security;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestUrl = getCurrentRequestUrl(req);
        List<String> roles = security.getConstraintList()
                .stream()
                .filter(constraint -> requestUrl.contains(constraint.getUrlPattern()))
                .findAny()
                .map(Constraint::getRole).orElse(Collections.emptyList());
        if (roles.isEmpty()) {
            chain.doFilter(req, resp);
        } else {
            User user = (User) req.getSession().getAttribute(CURRENT_USER);
            if (user == null) {
                req.getSession().setAttribute(SUCCESS_REDIRECT_URL_AFTER_SIGN_IN, requestUrl);
                req.getSession().setAttribute(AUTHORISATION_ERROR, AUTHORISATION_ERROR_TEXT);
                resp.sendRedirect(LOGIN_SERVLET);
            } else {
                if (!roles.contains(user.getRole())) {
                    req.getSession().setAttribute(STATUS_CODE, HttpServletResponse.SC_FORBIDDEN);
                    resp.sendRedirect(ERROR_SERVLET);
                } else {
                    chain.doFilter(req, resp);
                }
            }
        }
    }

    public String getCurrentRequestUrl(HttpServletRequest req) {
        String query = req.getQueryString();
        if (query == null) {
            return req.getRequestURI();
        } else {
            return req.getRequestURI() + "?" + query;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            security = new SecurityFactoryImpl().createSecurity(new File(System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/security.xml"));
        } catch (JAXBException e) {
            AUTHORIZATION_FILTER_LOGGER.error("Error during parsing security xml");
        }
    }

    @Override
    public void destroy() {
    }
}
