package com.epam.borysenko.strategy.imp;

import com.epam.borysenko.exception.ValidationException;
import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.strategy.CaptchaStorageStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static com.epam.borysenko.constants.ContextConstants.CAPTCHA;
import static com.epam.borysenko.constants.ContextConstants.CAPTCHA_MAP;

public class CookiesCaptchaStorageImpl implements CaptchaStorageStrategy {

    @Override
    @SuppressWarnings("unchecked")
    public void setCaptcha(Captcha captcha, HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie(CAPTCHA, captcha.getCaptchaID());
        resp.addCookie(cookie);
        Map<String, Captcha> captchaMap = (Map<String, Captcha>) req.getServletContext().getAttribute(CAPTCHA_MAP);
        captchaMap.put(captcha.getCaptchaID(), captcha);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Captcha getCaptcha(HttpServletRequest req) {
        Map<String, Captcha> captchaMap = (Map<String, Captcha>) req.getServletContext().getAttribute(CAPTCHA_MAP);
        return Optional.ofNullable(req.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> CAPTCHA.equals(cookie.getName()))
                        .findAny()
                        .map(Cookie::getValue)
                        .map(captchaMap::get)).orElseThrow(ValidationException::new);
    }
}
