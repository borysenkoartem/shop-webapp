package com.epam.borysenko.tag;

import com.epam.borysenko.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.CURRENT_USER;

public class AvatarTag extends SimpleTagSupport {

    private static final String IMG_SRC = "<img class=\"avatar\"src=\"/avatar\">";
    private static final String SIGN_IN_BUTTON = "<a class=\"btn btn-primary log-button\" href=\"/login\">Sign in</a>";
    private static final String LOGOUT_BUTTON = "<a class=\"btn btn-primary log-button\" href=\"/logout\">Logout</a>";

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER);
        if (currentUser == null) {
            getJspContext().getOut().write(SIGN_IN_BUTTON);
        } else {
            getJspContext().getOut().write(IMG_SRC + LOGOUT_BUTTON);
        }
    }
}

