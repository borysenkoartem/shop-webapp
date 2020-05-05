package com.epam.borysenko.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Captcha implements Serializable {

    private static final long serialVersionUID = -3486064910829205933L;

    private String captchaID;
    private String value;
    private BufferedImage bufferedImage;
    private LocalDateTime expireDate;

    public Captcha(String captchaID, String value, BufferedImage bufferedImage, LocalDateTime expireDate) {
        this.captchaID = captchaID;
        this.value = value;
        this.bufferedImage = bufferedImage;
        this.expireDate = expireDate;
    }

    public Captcha() {
    }

    public void setCaptchaID(String captchaID) {
        this.captchaID = captchaID;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getValue() {
        return value;
    }

    public String getCaptchaID() {
        return captchaID;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
}
