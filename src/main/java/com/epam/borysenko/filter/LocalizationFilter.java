package com.epam.borysenko.filter;

import com.epam.borysenko.factory.impl.LocaleServiceFactoryImpl;
import com.epam.borysenko.service.LocaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


import static com.epam.borysenko.constants.ContextConstants.DEFAULT_LOCALE;

@WebFilter(urlPatterns = {"/registration"},
        dispatcherTypes = {DispatcherType.REQUEST},
        initParams = {@WebInitParam(name = "storeType", value = "cookie"), //or value = "session"
                @WebInitParam(name = "defaultLanguage", value = "EN"),
                @WebInitParam(name = "availableLanguages", value = "EN,RU,UK"),
                @WebInitParam(name = "maxAge", value = "30")}
)
public class LocalizationFilter implements Filter {

    private static final Logger LOCALISATION_FILTER_LOGGER = LoggerFactory.getLogger(LocalizationFilter.class);
    private LocaleService localeService;
    private List<String> availableLanguages;
    private String defaultLanguage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String storeType = filterConfig.getInitParameter("storeType");
        String availableLanguagesGlued = filterConfig.getInitParameter("availableLanguages");
        localeService = new LocaleServiceFactoryImpl().createLocaleServiceForStoreType(storeType);
        availableLanguages = Arrays.asList(availableLanguagesGlued.split(","));
        defaultLanguage = filterConfig.getInitParameter("defaultLanguage");
        if (localeService == null || defaultLanguage == null) {
            defaultLanguage = DEFAULT_LOCALE;
            LOCALISATION_FILTER_LOGGER.error("Error during Localisation filter init");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Optional<String> localeString = Optional.ofNullable(req.getParameter("lang"));
        Locale locale;
        if (localeString.isPresent()) {
            locale = Locale.forLanguageTag(localeString.get());
        } else {
            locale = localeService.extractSavedLocale(req).orElse(chooseLocale(req.getLocales()));
        }
        localeService.saveLocale(locale, req, resp);
        req = wrapRequestLocale(req, locale);
        chain.doFilter(req, resp);
    }

    private Locale chooseLocale(final Enumeration<Locale> locales) {
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (availableLanguages.contains(locale.getLanguage().toUpperCase())) {
                return locale;
            }
        }
        return Locale.forLanguageTag(defaultLanguage);
    }

    private HttpServletRequest wrapRequestLocale(final HttpServletRequest req, final Locale locale) {
        Enumeration<Locale> locales = Collections.enumeration(Collections.singletonList(locale));
        return new HttpServletRequestWrapper(req) {
            @Override
            public Locale getLocale() {
                return locale;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return locales;
            }
        };
    }

    @Override
    public void destroy() {

    }
}
