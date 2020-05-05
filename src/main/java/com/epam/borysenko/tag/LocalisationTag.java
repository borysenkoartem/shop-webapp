package com.epam.borysenko.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


public class LocalisationTag extends SimpleTagSupport {

    private String lang;

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String flagSrc = "<button type=\"button\" class=\"flag-link\" value=\"" + lang + "\">\n" +
                "<img class=\"flag\" src=\"/media/" + lang + ".png\">\n" +
                "</button >";
        getJspContext().getOut().write(flagSrc);
    }
}
