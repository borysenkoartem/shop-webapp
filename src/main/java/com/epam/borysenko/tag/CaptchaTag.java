package com.epam.borysenko.tag;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.*;

public class CaptchaTag extends SimpleTagSupport {

    private static final String IMG_SRC = "<img src=\"/captcha\">";
    private static final String HIDDEN_FIELD_OUTPUT = "<img src=\"/captcha?captchaId=%s\">" + "<input name=\"captchaId\" type=\"hidden\" value=\"%s\"/>";

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        ServletContext servletContext = req.getServletContext();
        String captchaStorageStrategy = servletContext.getInitParameter(CAPTCHA_STORAGE_STRATEGY);

        if (captchaStorageStrategy.equals(HIDDEN_FIELD)) {
            String captchaID = (String) req.getAttribute(CAPTCHA_ID);
            String hiddenField = String.format(HIDDEN_FIELD_OUTPUT, captchaID, captchaID);
            getJspContext().getOut().write(hiddenField);
        } else {
            getJspContext().getOut().write(IMG_SRC);
        }
    }
}
