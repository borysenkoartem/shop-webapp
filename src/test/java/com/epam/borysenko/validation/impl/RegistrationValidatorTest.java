package com.epam.borysenko.validation.impl;

import com.epam.borysenko.model.form.RegistrationForm;
import com.epam.borysenko.service.UserService;
import com.epam.borysenko.validation.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.util.HashMap;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.USER_SERVICE;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationValidatorTest {

    private Validator<RegistrationForm> validator;
    private Map<String, String> result;
    @Mock
    private Part avatar;
    @Mock
    private ServletContext servletContext;
    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        validator = new RegistrationValidator(servletContext);
        result = new HashMap<>();
        when(avatar.getContentType()).thenReturn("application");
        when(servletContext.getAttribute(USER_SERVICE)).thenReturn(userService);
        when(userService.getAccountByLogin(any())).thenReturn(null);
    }

    @Test
    public void testValidationMethodValidateRegistrationFormWithWrongEmailErrorMapSizeWillBeOne() {
        RegistrationForm registrationForm = new RegistrationForm("test", "testtest.com",
                "123456789", "123456789", "name", "lastName", null, true);
        result = validator.validate(registrationForm);
        assertEquals(1, result.size());
    }

    @Test
    public void testValidationMethodValidateRegistrationFormWithWrongNameLastNameLoginErrorMapSizeWillBeThree() {
        RegistrationForm registrationForm = new RegistrationForm("1", "test_t@test.com",
                "123456789", "123456789", "1", "1", null, true);
        result = validator.validate(registrationForm);
        assertEquals(3, result.size());
    }

    @Test
    public void testValidationMethodValidateRegistrationFormWithShortPasswordErrorMapSizeWillBeOne() {
        RegistrationForm registrationForm = new RegistrationForm("test", "test_t@test.com",
                "123456", "1123456", "test", "test", null, true);
        result = validator.validate(registrationForm);
        assertEquals(1, result.size());
    }

    @Test
    public void testValidationMethodValidateRegistrationFormWithDifferentPasswordAndConfirmPasswordErrorMapSizeWillBeOne() {
        RegistrationForm registrationForm = new RegistrationForm("test", "test_t@test.com",
                "12345678", "11234567", "test", "test", null, true);
        result = validator.validate(registrationForm);
        assertEquals(1, result.size());
    }

    @Test
    public void testValidationMethodValidateRegistrationFormWithRightDataErrorMapSizeWillZero() {
        RegistrationForm registrationForm = new RegistrationForm("test", "test_t@test.com",
                "12345678", "12345678", "test", "test", null, true);
        result = validator.validate(registrationForm);
        assertEquals(0, result.size());
    }

    @Test
    public void testValidationMethodValidateRegistrationFormWithWrongPartTypeWillReturnOneError() {
        RegistrationForm registrationForm = new RegistrationForm("test", "test_t@test.com",
                "12345678", "12345678", "test", "test", avatar, true);
        result = validator.validate(registrationForm);
        assertEquals(1, result.size());
    }
}