package com.epam.borysenko.service.impl;

import com.epam.borysenko.service.LocaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

import static com.epam.borysenko.constants.ContextConstants.LOCALE;

public class SessionLocaleServiceImpl implements LocaleService {

    @Override
    public Optional<Locale> extractSavedLocale(HttpServletRequest req) {
        return Optional.of(Locale.forLanguageTag((String) req.getSession().getAttribute(LOCALE)));
    }


    @Override
    public void saveLocale(Locale locale, HttpServletRequest req, HttpServletResponse response) {
        req.getSession().setAttribute(LOCALE, locale.toLanguageTag());
    }
}
