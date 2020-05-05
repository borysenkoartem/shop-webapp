package com.epam.borysenko.validation.impl;

import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.service.CaptchaService;
import com.epam.borysenko.validation.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.epam.borysenko.constants.ContextConstants.CAPTCHA_PARAMETER;
import static com.epam.borysenko.constants.ContextConstants.CAPTCHA_SERVICE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaValidatorTest {

    private static final String TEST_STRING = "test";
    private Validator<HttpServletRequest> validator;
    private Map<String, String> result;
    @Mock
    private HttpServletRequest req;
    @Mock
    private CaptchaService captchaService;
    @Mock
    private ServletContext servletContext;
    private Captcha captcha;

    @Before
    public void setUp() {

        validator = new CaptchaValidator();
        result = new HashMap<>();
        when(req.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(CAPTCHA_SERVICE)).thenReturn(captchaService);

    }

    @Test
    public void testValidateMethodWrongCaptchaValueWithCaptchaParameterErrorSizeWillBeOne() {
        LocalDateTime expireDate = LocalDateTime.now().plusMinutes(5);
        captcha = new Captcha(TEST_STRING, TEST_STRING, null, expireDate);
        when(captchaService.getCaptcha(req)).thenReturn(captcha);
        when(req.getParameter(CAPTCHA_PARAMETER)).thenReturn(CAPTCHA_PARAMETER);
        result = validator.validate(req);
        assertEquals(1, result.size());
    }

    @Test
    public void testValidateMethodExpireCaptchaErrorSizeWillBeOne() {
        LocalDateTime expireDate = LocalDateTime.now().minusMinutes(5);
        captcha = new Captcha(TEST_STRING, TEST_STRING, null, expireDate);
        when(captchaService.getCaptcha(req)).thenReturn(captcha);
        when(req.getParameter(CAPTCHA_PARAMETER)).thenReturn(TEST_STRING);
        result = validator.validate(req);
        assertEquals(1, result.size());
    }

    @Test
    public void testValidateMethodCorrectCaptchaErrorSizeWillBeOne() {
        LocalDateTime expireDate = LocalDateTime.now().plusMinutes(5);
        captcha = new Captcha(TEST_STRING, TEST_STRING, null, expireDate);
        when(captchaService.getCaptcha(req)).thenReturn(captcha);
        when(req.getParameter(CAPTCHA_PARAMETER)).thenReturn(TEST_STRING);
        result = validator.validate(req);
        assertEquals(0, result.size());
    }
}