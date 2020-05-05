package com.epam.borysenko.strategy.imp;

import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.strategy.CaptchaStorageStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.borysenko.constants.ContextConstants.CAPTCHA;

public class SessionCaptchaStorageImpl implements CaptchaStorageStrategy {

    @Override
    public void setCaptcha(Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(CAPTCHA, captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        return (Captcha) request.getSession().getAttribute(CAPTCHA);
    }
}
