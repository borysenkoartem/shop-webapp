package com.epam.borysenko.servlet.page;

import com.epam.borysenko.entity.product.Category;
import com.epam.borysenko.entity.product.Producer;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.entity.ShoppingCart;
import com.epam.borysenko.service.ProductService;
import com.epam.borysenko.servlet.AbstractServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.borysenko.constants.ContextConstants.*;
import static com.epam.borysenko.constants.PathConstants.*;
import static com.epam.borysenko.constants.SearchFormConstant.CATEGORIES;
import static com.epam.borysenko.constants.SearchFormConstant.PRODUCERS;

@WebServlet(SHOPPING_CART_SERVLET)
public class ShoppingCartServlet extends AbstractServlet {

    private static final Logger SHOPPING_SERVLET_LOGGER = LoggerFactory.getLogger(ShoppingCartServlet.class);

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute(PRODUCT_SERVICE);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(SHOPPING_CART);
        if (shoppingCart.getTotalCount() > 0) {
            try {
                List<Category> categories = productService.listAllCategories();
                List<Producer> producers = productService.listAllProducer();
                req.setAttribute(CATEGORIES, categories);
                req.setAttribute(PRODUCERS, producers);
                forwardToPage(SHOPPING_CARD_PAGE, req, resp);
            } catch (ServiceException e) {
                SHOPPING_SERVLET_LOGGER.error(e.getMessage());
            }
        } else {
            resp.sendRedirect(PRODUCTS_SERVLET);
        }
    }
}
