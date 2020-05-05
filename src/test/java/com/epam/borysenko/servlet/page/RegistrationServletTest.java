package com.epam.borysenko.servlet.page;

import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.factory.UserFactory;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.model.form.RegistrationForm;
import com.epam.borysenko.service.CaptchaService;
import com.epam.borysenko.service.UserService;
import com.epam.borysenko.servlet.page.RegistrationServlet;
import com.epam.borysenko.validation.impl.CaptchaValidator;
import com.epam.borysenko.validation.impl.RegistrationValidator;
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
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collections;

import static com.epam.borysenko.constants.ContextConstants.*;
import static com.epam.borysenko.constants.PathConstants.*;
import static com.epam.borysenko.constants.RegistrationFormConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest {

    @Mock
    private HttpServletResponse reps;
    @Mock
    private HttpServletRequest req;
    @Mock
    private CaptchaService captchaService;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession session;
    @Mock
    private User user;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private UserFactory userFactory;
    @Mock
    private UserService userService;
    @Mock
    private RegistrationValidator registrationValidationUtil;
    @Mock
    private CaptchaValidator captchaValidationUtil;
    @Mock
    private Part avatar;
    @Mock
    RegistrationForm registrationForm;

    @InjectMocks
    private RegistrationServlet registrationServlet;

    @Before
    public void setUp() {
        when(req.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(CAPTCHA_SERVICE)).thenReturn(captchaService);
        when(req.getSession()).thenReturn(session);
        when(servletContext.getAttribute(USER_FACTORY)).thenReturn(userFactory);
        when(servletContext.getAttribute(USER_SERVICE)).thenReturn(userService);
        when(req.getRequestDispatcher(PAGE_TEMPLATE)).thenReturn(requestDispatcher);
        when(servletContext.getAttribute(CAPTCHA_VALIDATION_UTIL)).thenReturn(captchaValidationUtil);
        when(servletContext.getAttribute(REGISTRATION_VALIDATION_UTIL)).thenReturn(registrationValidationUtil);

    }

    @Test
    public void testDoGetMethodWithUserAtSessionResponseCallMethodSendRedirect() throws ServletException, IOException {
        when(session.getAttribute(CURRENT_USER)).thenReturn(user);
        registrationServlet.doGet(req, reps);
        verify(reps).sendRedirect(HOME_SERVLET);
    }

    @Test
    public void testDoGetMethodVerifyCaptchaServiceCallCreateCaptchaMethod() throws ServletException, IOException {
        when(session.getAttribute(CURRENT_USER)).thenReturn(null);
        registrationServlet.doGet(req, reps);
        verify(captchaService).createCaptcha(req, reps);
    }

    @Test
    public void testDoGetMethodVerifyRequestDispatcherCallForwardMethod() throws ServletException, IOException {
        when(session.getAttribute(CURRENT_USER)).thenReturn(null);
        registrationServlet.doGet(req, reps);
        verify(requestDispatcher).forward(req, reps);
    }

    @Test
    public void testDoPostMethodWithRightRegistrationFormSetCurrentAccountToSession() throws ServletException, IOException, ServiceException {
        when(registrationValidationUtil.validate(any())).thenReturn(Collections.emptyMap());
        when(captchaValidationUtil.validate(any())).thenReturn(Collections.emptyMap());
        when(userFactory.createUser(any())).thenReturn(user);
        when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        when(req.getParameter(EMAIL)).thenReturn("TEST@mail.com");
        when(req.getParameter(PASSWORD)).thenReturn(PASSWORD);
        when(req.getParameter(CONFIRM_PASSWORD)).thenReturn(PASSWORD);
        when(req.getParameter(FIRST_NAME)).thenReturn(FIRST_NAME);
        when(req.getParameter(LAST_NAME)).thenReturn(LAST_NAME);
        when(req.getPart(AVATAR)).thenReturn(avatar);
        when(avatar.getContentType()).thenReturn("application/octet-stream");
        when(user.getAvatarLink()).thenReturn("basic.png");
        when(userService.getAccountByLogin(user.getLogin())).thenReturn(null).thenReturn(user);
        registrationServlet.doPost(req, reps);
        verify(session).setAttribute(CURRENT_USER, user);
    }

    @Test
    public void testDoPostMethodWithAlreadyExistUserAtDataBaseRegistrationForm() throws ServletException, IOException, ServiceException {
        when(registrationValidationUtil.validate(any())).thenReturn(Collections.emptyMap());
        when(captchaValidationUtil.validate(any())).thenReturn(Collections.emptyMap());
        when(userFactory.createUser(any())).thenReturn(user);
        when(userService.getAccountByLogin(any())).thenReturn(user);
        when(req.getParameter(LOGIN)).thenReturn(LOGIN);
        when(req.getParameter(EMAIL)).thenReturn("TEST@mail.com");
        when(req.getParameter(PASSWORD)).thenReturn(PASSWORD);
        when(req.getParameter(CONFIRM_PASSWORD)).thenReturn(PASSWORD);
        when(req.getParameter(FIRST_NAME)).thenReturn(FIRST_NAME);
        when(req.getParameter(LAST_NAME)).thenReturn(LAST_NAME);
        when(req.getPart(AVATAR)).thenReturn(avatar);
        when(avatar.getContentType()).thenReturn("application/octet-stream");

        registrationServlet.doPost(req, reps);
        verify(reps).sendRedirect(REGISTRATION_SERVLET);
    }
}