package com.epam.borysenko.servlet.page;

import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.factory.UserFactory;
import com.epam.borysenko.model.form.RegistrationForm;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.service.CaptchaService;
import com.epam.borysenko.service.UserService;
import com.epam.borysenko.servlet.AbstractServlet;
import com.epam.borysenko.validation.impl.CaptchaValidator;
import com.epam.borysenko.validation.impl.RegistrationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.*;
import static com.epam.borysenko.constants.PathConstants.*;
import static com.epam.borysenko.constants.RegistrationFormConstants.*;
import static com.epam.borysenko.constants.ValidationConstants.*;

@WebServlet(REGISTRATION_SERVLET)
@MultipartConfig(fileSizeThreshold = 1048576, maxFileSize = 20848820, maxRequestSize = 418018841)
public class RegistrationServlet extends AbstractServlet {

    private static final long serialVersionUID = 6648851613816260851L;
    private static final Logger REGISTRATION_LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(UTF8);
        CaptchaService captchaService = (CaptchaService) req.getServletContext().getAttribute(CAPTCHA_SERVICE);
        captchaService.createCaptcha(req, resp);
        if (req.getSession().getAttribute(CURRENT_USER) == null) {
            setAttributeFromSessionToRequest(req, resp);
        } else {
            resp.sendRedirect(HOME_SERVLET);
        }
    }

    private void setAttributeFromSessionToRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(REGISTRATION_FORM, req.getSession().getAttribute(REGISTRATION_FORM));
        req.getSession().removeAttribute(REGISTRATION_FORM);
        req.setAttribute(ERRORS, req.getSession().getAttribute(ERRORS));
        req.getSession().removeAttribute(ERRORS);
        forwardToPage(REGISTRATION_PAGE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(UTF8);
        try {
            Map<String, String> errors = new HashMap<>();
            RegistrationForm registrationForm = getAndValidateRegistrationForm(req, errors);
            validateCaptcha(req, errors);
            if (errors.isEmpty()) {
                UserFactory userFactory = (UserFactory) req.getServletContext().getAttribute(USER_FACTORY);
                User newUser = userFactory.createUser(registrationForm);
                UserService userService = (UserService) req.getServletContext().getAttribute(USER_SERVICE);
                if (userService.getAccountByLogin(newUser.getLogin()) == null) {
                    userService.createAccount(newUser);
                    saveAvatar(req, newUser);
                    User currentUser = userService.getAccountByLogin(newUser.getLogin());
                    req.getSession().setAttribute(CURRENT_USER, currentUser);
                } else {
                    errors.put(LOGIN_ERROR, LOGIN_ALREADY_EXIST_ERROR_TEXT);
                    req.getSession().setAttribute(ERRORS, errors);
                    resp.sendRedirect(REGISTRATION_SERVLET);
                }
            } else {
                req.getSession().setAttribute(REGISTRATION_FORM, registrationForm);
                req.getSession().setAttribute(ERRORS, errors);
                resp.sendRedirect(REGISTRATION_SERVLET);
            }
        } catch (ServiceException e) {
            REGISTRATION_LOGGER.error("Error:" + e.getMessage());
        }
    }

    private RegistrationForm createRegistrationForm(HttpServletRequest req) throws IOException, ServletException {
        String login = req.getParameter(LOGIN);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        String confirmPassword = req.getParameter(CONFIRM_PASSWORD);
        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        boolean newsletterConsent = ON.equals(req.getParameter(NEWSLETTER_CONSENT));
        Part avatar = req.getPart(AVATAR);
        if ("application/octet-stream".equalsIgnoreCase(avatar.getContentType())) {
            avatar = null;
        }
        return new RegistrationForm(login, email, password,
                confirmPassword, firstName, lastName, avatar, newsletterConsent);
    }


    private RegistrationForm getAndValidateRegistrationForm(HttpServletRequest req, Map<String, String> errors) throws IOException, ServletException {
        RegistrationForm registrationForm = createRegistrationForm(req);
        RegistrationValidator registrationValidationUtil = (RegistrationValidator) req.getServletContext().getAttribute(REGISTRATION_VALIDATION_UTIL);
        errors.putAll(registrationValidationUtil.validate(registrationForm));
        return registrationForm;
    }


    private void validateCaptcha(HttpServletRequest req, Map<String, String> errors) {
        CaptchaValidator captchaValidationUtil = (CaptchaValidator) req.getServletContext().getAttribute(CAPTCHA_VALIDATION_UTIL);
        errors.putAll(captchaValidationUtil.validate(req));
    }

    private void saveAvatar(HttpServletRequest req, User user) throws IOException, ServletException {
        if (!user.getAvatarLink().contains(BASIC_IMAGE_NAME)) {
            Part avatar = req.getPart(AVATAR);
            String imagePath = AVATAR_PATH + user.getAvatarLink();
            avatar.write(imagePath);
        }
    }
}
