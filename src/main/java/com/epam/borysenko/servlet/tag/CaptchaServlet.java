package com.epam.borysenko.servlet.tag;

import com.epam.borysenko.model.Captcha;
import com.epam.borysenko.service.CaptchaService;
import com.epam.borysenko.servlet.AbstractServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.*;
import static com.epam.borysenko.constants.PathConstants.CAPTCHA_SERVLET;

@WebServlet(urlPatterns = CAPTCHA_SERVLET)
public class CaptchaServlet extends AbstractServlet {

    private static final Logger CAPTCHA_SERVLET_LOGGER = LoggerFactory.getLogger(CaptchaServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CaptchaService captchaService = (CaptchaService) req.getServletContext().getAttribute(CAPTCHA_SERVICE);
        String captchaStorageStrategy = req.getServletContext().getInitParameter(CAPTCHA_STORAGE_STRATEGY);

        if (captchaStorageStrategy.equals(HIDDEN_FIELD)) {
            String captchaId = req.getParameter(CAPTCHA_ID);
            req.setAttribute(CAPTCHA_ID, captchaId);
        }
        Captcha captcha = captchaService.getCaptcha(req);
        try {
            ImageIO.write(captcha.getBufferedImage(), "png", resp.getOutputStream());
        } catch (IOException ex) {
            CAPTCHA_SERVLET_LOGGER.debug(ex.getMessage());
        }
    }
}
