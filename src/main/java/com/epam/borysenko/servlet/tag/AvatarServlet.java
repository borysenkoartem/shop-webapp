package com.epam.borysenko.servlet.tag;

import com.epam.borysenko.entity.User;
import com.epam.borysenko.servlet.AbstractServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.CURRENT_USER;
import static com.epam.borysenko.constants.PathConstants.AVATAR_PATH;
import static com.epam.borysenko.constants.PathConstants.AVATAR_SERVLET;
import static com.epam.borysenko.constants.RegistrationFormConstants.BASIC_IMAGE_NAME;

@WebServlet(AVATAR_SERVLET)
public class AvatarServlet extends AbstractServlet {

    private static final Logger AVATAR_SERVLET_LOGGER = LoggerFactory.getLogger(AvatarServlet.class);
    private static final long serialVersionUID = 6708623756365637930L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER);
        String avatarName = currentUser.getAvatarLink();
        String imageExtension;
        if (avatarName.equalsIgnoreCase(BASIC_IMAGE_NAME)) {
            imageExtension = "png";
        } else {
            imageExtension = currentUser.getAvatarLink().replace(currentUser.getLogin() + ".", "");
        }
        resp.setContentType("image/" + imageExtension);
        try {
            File file = new File(AVATAR_PATH + currentUser.getAvatarLink());
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(file));
            if (bufferedImage == null) {
                bufferedImage = ImageIO.read(new File(AVATAR_PATH, BASIC_IMAGE_NAME));
            }
            ImageIO.write(bufferedImage, imageExtension, resp.getOutputStream());
        } catch (IOException ex) {
            AVATAR_SERVLET_LOGGER.debug(ex.getMessage());
        }
    }
}
