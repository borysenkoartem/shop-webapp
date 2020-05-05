package com.epam.borysenko.factory.impl;

import com.epam.borysenko.factory.SecurityFactory;
import com.epam.borysenko.model.Security;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class SecurityFactoryImpl implements SecurityFactory {

    @Override
    public Security createSecurity(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Security.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Security) jaxbUnmarshaller.unmarshal(file);
    }
}
