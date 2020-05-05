package com.epam.borysenko.factory;

import com.epam.borysenko.model.Security;

import javax.xml.bind.JAXBException;
import java.io.File;

public interface SecurityFactory {

    Security createSecurity(File file) throws JAXBException;
}
