package com.epam.borysenko.factory;

import com.epam.borysenko.entity.User;
import com.epam.borysenko.model.form.RegistrationForm;

public interface UserFactory {

    User createUser(RegistrationForm registrationForm);
}
