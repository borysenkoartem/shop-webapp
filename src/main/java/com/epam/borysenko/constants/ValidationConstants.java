package com.epam.borysenko.constants;

public final class ValidationConstants {

    public static final String NAME_REGEX = "[A-Za-zА-Яа-я]{1,30}";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String LOGIN_ERROR_TEXT = "Wrong login, it should be 1-30 letters";
    public static final String LOGIN_ALREADY_EXIST_ERROR_TEXT = "Login already exist";

    public static final String FIRST_NAME_ERROR = "FIRST_NAME_ERROR";
    public static final String FIRST_NAME_ERROR_TEXT = "Wrong first name, it should be 1-30 letters";

    public static final String LAST_NAME_ERROR = "LAST_NAME_ERROR";
    public static final String LAST_NAME_ERROR_TEXT = "Wrong last name, it should be 1-30 letters";

    public static final String EMAIL_REGEX = "\\S+@\\S+\\.\\S+";
    public static final String EMAIL_ERROR = "EMAIL_ERROR";
    public static final String EMAIL_ERROR_TEXT = "Invalid email address";

    public static final String PASSWORD_REGEX = "^\\w{8,}$";
    public static final String PASSWORD_ERROR = "PASSWORD_ERROR";
    public static final String PASSWORD_ERROR_TEXT = "Wrong password, it should be more than 8 symbols";

    public static final String CONFIRM_PASSWORD_ERROR = "CONFIRM_PASSWORD_ERROR";
    public static final String CONFIRM_PASSWORD_ERROR_TEXT = "Wrong password confirmation, it should be similar to password";

    public static final String CAPTCHA_ERROR = "CAPTCHA_ERROR";
    public static final String CAPTCHA_ERROR_TEXT = "Wrong captcha";
    public static final String CAPTCHA_EXPIRED_ERROR_TEXT = "Captcha has expired please try again";

    public static final String REGISTRATION_FORM = "REGISTRATION_FORM";
    public static final String ERRORS = "ERRORS";

    public static final String AVATAR_ERROR = "AVATAR_ERROR";
    public static final String AVATAR_ERROR_TEXT = "Wrong avatar format";

    public static final String LOGIN_ERROR_VALIDATION_TEXT = "Wrong login or password";
    public static final String WRONG_PRICE = "WRONG_PRICE";
    public static final String WRONG_PRICE_FORMAT_TEXT = "Wrong  price formal";

    public static final String WRONG_PRICE_VALUE_TEXT = "Min price bigger than max price";

    private ValidationConstants() {
    }
}
