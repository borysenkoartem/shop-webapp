package com.epam.borysenko.service;

import com.epam.borysenko.model.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Captcha Service interface.
 *
 * @author Artem Borysenko.
 */
public interface CaptchaService {

    /**
     * Creating captcha and set information about it to HTTP servlet.
     *
     * @param req  HttpServletRequest.
     * @param resp HttpServletResponse.
     */
    void createCaptcha(HttpServletRequest req, HttpServletResponse resp);

    /**
     * Get captcha from HttpServletRequest.
     *
     * @param req HttpServletRequest.
     * @return Captcha.
     */
    Captcha getCaptcha(HttpServletRequest req);

}
