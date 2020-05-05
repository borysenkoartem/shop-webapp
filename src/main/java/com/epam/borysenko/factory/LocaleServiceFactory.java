package com.epam.borysenko.factory;

import com.epam.borysenko.service.LocaleService;

public interface LocaleServiceFactory {

    LocaleService createLocaleServiceForStoreType(final String storeType);
}
