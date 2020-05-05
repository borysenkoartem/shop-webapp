package com.epam.borysenko.listener;

import com.epam.borysenko.entity.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static com.epam.borysenko.constants.ContextConstants.SHOPPING_CART;

@WebListener
public class ApplicationSessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(SHOPPING_CART, new ShoppingCart());
        LOGGER.debug("Created " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.debug("Destroyed " + se.getSession().getId());
    }
}

