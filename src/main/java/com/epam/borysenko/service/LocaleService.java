package com.epam.borysenko.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public interface LocaleService {

    Optional<Locale> extractSavedLocale(HttpServletRequest request);

    void saveLocale(Locale locale, HttpServletRequest req, HttpServletResponse resp);
}
