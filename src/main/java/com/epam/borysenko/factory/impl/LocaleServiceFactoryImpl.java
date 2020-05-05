package com.epam.borysenko.factory.impl;

import com.epam.borysenko.factory.LocaleServiceFactory;
import com.epam.borysenko.service.LocaleService;
import com.epam.borysenko.service.impl.CookieLocaleServiceImpl;
import com.epam.borysenko.service.impl.SessionLocaleServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class LocaleServiceFactoryImpl implements LocaleServiceFactory {

    private Map<String, LocaleService> serviceMap;

    public LocaleServiceFactoryImpl() {
        serviceMap = new HashMap<>();
        serviceMap.put("cookie", new CookieLocaleServiceImpl());
        serviceMap.put("session", new SessionLocaleServiceImpl());
    }

    @Override
    public LocaleService createLocaleServiceForStoreType(String storeType) {
            return serviceMap.get(storeType);
    }
}
