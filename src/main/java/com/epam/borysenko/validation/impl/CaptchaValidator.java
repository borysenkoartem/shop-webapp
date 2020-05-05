package com.epam.borysenko.validation.impl;

import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.service.CaptchaService;
import com.epam.borysenko.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.CAPTCHA_PARAMETER;
import static com.epam.borysenko.constants.ContextConstants.CAPTCHA_SERVICE;
import static com.epam.borysenko.constants.ValidationConstants.*;

public class CaptchaValidator implements Validator<HttpServletRequest> {

    @Override
    public Map<String, String> validate(HttpServletRequest req) {
        String captchaFormRegistrationForm = req.getParameter(CAPTCHA_PARAMETER);
        CaptchaService captchaService = (CaptchaService) req.getServletContext().getAttribute(CAPTCHA_SERVICE);
        Captcha captcha = captchaService.getCaptcha(req);
        Map<String, String> validationResult = new HashMap<>();
        if (captcha == null || captcha.getExpireDate().isBefore(LocalDateTime.now())) {
            validationResult.put(CAPTCHA_ERROR, CAPTCHA_EXPIRED_ERROR_TEXT);
        } else if (!captchaFormRegistrationForm.equals(captcha.getValue())) {
            validationResult.put(CAPTCHA_ERROR, CAPTCHA_ERROR_TEXT);
        }
        return validationResult;
    }
}
