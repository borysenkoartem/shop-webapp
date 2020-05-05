package com.epam.borysenko.validation.impl;

import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.model.form.RegistrationForm;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.service.UserService;
import com.epam.borysenko.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.USER_SERVICE;
import static com.epam.borysenko.constants.ValidationConstants.*;

public class RegistrationValidator implements Validator<RegistrationForm> {

    private static final Logger REGISTRATION_VALIDATOR_LOGGER = LoggerFactory.getLogger(RegistrationValidator.class);

    private ServletContext servletContext;

    public RegistrationValidator(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public Map<String, String> validate(RegistrationForm registrationForm) {

        Map<String, String> validationResult = new HashMap<>();
        if (registrationForm.getLogin().matches(NAME_REGEX)) {
            UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
            try {
                User user = userService.getAccountByLogin(registrationForm.getLogin());
                if (user != null) {
                    validationResult.put(LOGIN_ERROR, LOGIN_ALREADY_EXIST_ERROR_TEXT);
                }
            } catch (ServiceException e) {
                REGISTRATION_VALIDATOR_LOGGER.error("Error: " + e.getMessage());
            }
        } else {
            validationResult.put(LOGIN_ERROR, LOGIN_ERROR_TEXT);
        }
        if (!registrationForm.getFirstName().matches(NAME_REGEX)) {
            validationResult.put(FIRST_NAME_ERROR, FIRST_NAME_ERROR_TEXT);
        }
        if (!registrationForm.getLastName().matches(NAME_REGEX)) {
            validationResult.put(LAST_NAME_ERROR, LAST_NAME_ERROR_TEXT);
        }
        if (!registrationForm.getEmail().matches(EMAIL_REGEX)) {
            validationResult.put(EMAIL_ERROR, EMAIL_ERROR_TEXT);
        }
        if (!registrationForm.getPassword().matches(PASSWORD_REGEX)) {
            validationResult.put(PASSWORD_ERROR, PASSWORD_ERROR_TEXT);
        } else if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            validationResult.put(CONFIRM_PASSWORD_ERROR, CONFIRM_PASSWORD_ERROR_TEXT);
        }
        if (registrationForm.getAvatar() != null && !registrationForm.getAvatar().getContentType().startsWith("image")) {
                validationResult.put(AVATAR_ERROR, AVATAR_ERROR_TEXT);
            }
        return validationResult;
        }
}
