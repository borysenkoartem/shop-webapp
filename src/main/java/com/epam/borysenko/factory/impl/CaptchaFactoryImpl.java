package com.epam.borysenko.factory.impl;

import com.epam.borysenko.factory.CaptchaFactory;
import com.epam.borysenko.model.Captcha;
import com.github.cage.Cage;
import com.github.cage.YCage;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class CaptchaFactoryImpl implements CaptchaFactory {

    public static final int MIN_CAPTCHA_VALUE = 100000;
    public static final int MAX_CAPTCHA_VALUE = 899999;
    public static final int AFTER_WHAT_TIME_CAPTCHA_EXPIRED = 1;

    @Override
    public Captcha createCaptcha() {
        Captcha captcha = new Captcha();
        captcha.setValue(String.valueOf(new Random().nextInt(MAX_CAPTCHA_VALUE) + MIN_CAPTCHA_VALUE));
        captcha.setCaptchaID(UUID.randomUUID().toString());
        captcha.setBufferedImage(drawCaptcha(captcha.getValue()));
        captcha.setExpireDate(LocalDateTime.now().plusMinutes(AFTER_WHAT_TIME_CAPTCHA_EXPIRED));
        return captcha;
    }

    public BufferedImage drawCaptcha(String captcha) {
        Cage cage = new YCage();
        return cage.drawImage(captcha);
    }
}
