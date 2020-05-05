package com.epam.borysenko.factory.impl;

import com.epam.borysenko.factory.UserFactory;
import com.epam.borysenko.model.form.RegistrationForm;
import com.epam.borysenko.entity.User;

import static com.epam.borysenko.constants.RegistrationFormConstants.*;

public class UserFactoryImpl implements UserFactory {

    @Override
    public User createUser(RegistrationForm registrationForm) {
        String avatarName;
        if (registrationForm.getAvatar() == null) {
            avatarName = BASIC_IMAGE_NAME;
        } else {
            avatarName = registrationForm.getLogin()
                    + registrationForm.getAvatar().getContentType().replace("image/", ".");
        }
        return new User(registrationForm.getLogin(), registrationForm.getEmail(),
                registrationForm.getPassword(), registrationForm.getFirstName(),
                registrationForm.getLastName(), registrationForm.isNewsletterConsent(), avatarName);
    }
}
