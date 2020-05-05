package com.epam.borysenko.strategy;

import com.epam.borysenko.model.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaStorageStrategy {

    void setCaptcha(Captcha captcha, HttpServletRequest request, HttpServletResponse response);

    Captcha getCaptcha(HttpServletRequest request);
}
