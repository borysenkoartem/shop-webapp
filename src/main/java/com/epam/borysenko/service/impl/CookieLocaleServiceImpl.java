package com.epam.borysenko.service.impl;

import com.epam.borysenko.service.LocaleService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import static com.epam.borysenko.constants.ContextConstants.COOLIE_MAX_AGE;
import static com.epam.borysenko.constants.ContextConstants.LOCALE;

public class CookieLocaleServiceImpl implements LocaleService {

    @Override
    public Optional<Locale> extractSavedLocale(HttpServletRequest request) {
        return Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> LOCALE.equals(cookie.getName()))
                        .findAny()
                        .map(Cookie::getValue))
                .map(Locale::forLanguageTag);
    }


    @Override
    public void saveLocale(Locale locale, HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie(LOCALE, locale.toLanguageTag());
        cookie.setMaxAge(Integer.parseInt(req.getServletContext().getInitParameter(COOLIE_MAX_AGE)));
        resp.addCookie(cookie);
    }
}
