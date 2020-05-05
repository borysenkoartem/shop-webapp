package com.epam.borysenko.servlet.page;

import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.CURRENT_USER;
import static com.epam.borysenko.constants.ContextConstants.USER_SERVICE;
import static com.epam.borysenko.constants.PathConstants.HOME_SERVLET;
import static com.epam.borysenko.constants.PathConstants.PAGE_TEMPLATE;
import static com.epam.borysenko.constants.RegistrationFormConstants.LOGIN;
import static com.epam.borysenko.constants.RegistrationFormConstants.PASSWORD;
import static com.epam.borysenko.constants.ValidationConstants.LOGIN_ERROR;
import static com.epam.borysenko.constants.ValidationConstants.LOGIN_ERROR_VALIDATION_TEXT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {

    private static final String TEST_PASSWORD="PASSWORD";

    @InjectMocks
    private LoginServlet loginServlet;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletResponse reps;
    @Mock
    private HttpServletRequest req;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession session;
    @Mock
    private User user;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        when(req.getServletContext()).thenReturn(servletContext);
        when(req.getSession()).thenReturn(session);
        when(servletContext.getAttribute(USER_SERVICE)).thenReturn(userService);
        when(req.getRequestDispatcher(PAGE_TEMPLATE)).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGetMethodWithUserAtSessionResponseCallMethodSendRedirect() throws ServletException, IOException {
        when(session.getAttribute(CURRENT_USER)).thenReturn(user);
        loginServlet.doGet(req, reps);
        verify(reps).sendRedirect(HOME_SERVLET);
    }
    @Test
    public void testDoGetMethodWithoutUserAtSession() throws ServletException, IOException {
        when(session.getAttribute(CURRENT_USER)).thenReturn(null);
        loginServlet.doGet(req, reps);
        verify(requestDispatcher).forward(req, reps);
    }

    @Test
    public void testDoPostMethodWithWrongLoginSetAttributeLoginErrorToSession() throws ServletException, IOException, ServiceException {
        when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        when(userService.getAccountByLogin(LOGIN)).thenReturn(null);
        loginServlet.doPost(req, reps);
        verify(session).setAttribute(LOGIN_ERROR, LOGIN_ERROR_VALIDATION_TEXT);
    }

    @Test
    public void testDoPostMethodWithWrongPasswordSetAttributeLoginErrorToSession() throws ServletException, IOException, ServiceException {
        when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        when(userService.getAccountByLogin(LOGIN)).thenReturn(user);
        when(user.getPassword()).thenReturn(TEST_PASSWORD);
        when(req.getParameter(PASSWORD)).thenReturn(PASSWORD);
        loginServlet.doPost(req, reps);
        verify(session).setAttribute(LOGIN_ERROR, LOGIN_ERROR_VALIDATION_TEXT);
    }

    @Test
    public void testDoPostMethodWithCorrectLoginAndPasswordSetAttributeCurrentUserToSession() throws ServletException, IOException, ServiceException {
        when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        when(userService.getAccountByLogin(LOGIN)).thenReturn(user);
        when(user.getPassword()).thenReturn(TEST_PASSWORD);
        when(req.getParameter(PASSWORD)).thenReturn(TEST_PASSWORD);
        loginServlet.doPost(req, reps);
        verify(session).setAttribute(CURRENT_USER, user);
    }
}