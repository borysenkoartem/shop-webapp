package com.epam.borysenko.model.form;

import javax.servlet.http.Part;
import java.io.Serializable;

public class RegistrationForm implements Serializable {

    private static final long serialVersionUID = 5266375860250870263L;

    private String login;
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private Part avatar;
    private boolean newsletterConsent;

    public RegistrationForm(String login, String email, String password,
                            String confirmPassword, String firstName,
                            String lastName, Part avatar, boolean newsletterConsent) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.newsletterConsent = newsletterConsent;
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Part getAvatar() {
        return avatar;
    }

    public boolean isNewsletterConsent() {
        return newsletterConsent;
    }

}
