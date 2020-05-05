package com.epam.borysenko.strategy.imp;

import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.strategy.CaptchaStorageStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.CAPTCHA_MAP;

public class HiddenFieldStorageImpl implements CaptchaStorageStrategy {

    private static final String CAPTCHA_ID = "captchaId";

    @Override
    @SuppressWarnings("unchecked")
    public void setCaptcha(Captcha captcha, HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Captcha> captchaMap = (Map<String, Captcha>) req.getServletContext().getAttribute(CAPTCHA_MAP);
        captchaMap.put(captcha.getCaptchaID(), captcha);
        req.setAttribute(CAPTCHA_ID, captcha.getCaptchaID());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Captcha getCaptcha(HttpServletRequest req) {
        String captchaID = req.getParameter(CAPTCHA_ID);
        Map<String, Captcha> captchaMap = (Map<String, Captcha>)
                req.getServletContext().getAttribute(CAPTCHA_MAP);
        return captchaMap.get(captchaID);
    }
}
