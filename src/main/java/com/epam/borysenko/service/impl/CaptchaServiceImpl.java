package com.epam.borysenko.service.impl;

import com.epam.borysenko.factory.CaptchaFactory;
import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.service.CaptchaService;
import com.epam.borysenko.strategy.CaptchaStorageStrategy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.*;

public class CaptchaServiceImpl implements CaptchaService {

    private CaptchaStorageStrategy captchaStorageStrategy;
    private CaptchaFactory captchaFactory;


    public CaptchaServiceImpl(ServletContext servletContext) {
        captchaStorageStrategy = (CaptchaStorageStrategy) servletContext.getAttribute(CAPTCHA_STORAGE_STRATEGY);
        captchaFactory = (CaptchaFactory) servletContext.getAttribute(CAPTCHA_FACTORY);
    }

    @Override
    public void createCaptcha(HttpServletRequest req, HttpServletResponse resp) {
        cleanCaptchaMap(req);
        captchaStorageStrategy.setCaptcha(captchaFactory.createCaptcha(), req, resp);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest req) {
        return captchaStorageStrategy.getCaptcha(req);
    }

    private void cleanCaptchaMap(HttpServletRequest req) {
        Map<String, Captcha> captchaMap = (Map<String, Captcha>) req.getServletContext().getAttribute(CAPTCHA_MAP);
        captchaMap.values()
                .stream()
                .filter(captcha -> captcha.getExpireDate().isBefore(LocalDateTime.now()))
                .forEach(captcha -> captchaMap.remove(captcha.getCaptchaID()));
    }
}
