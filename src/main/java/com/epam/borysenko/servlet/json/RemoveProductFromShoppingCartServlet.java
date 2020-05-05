package com.epam.borysenko.servlet.json;

import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.entity.ShoppingCart;
import com.epam.borysenko.model.form.ProductForm;
import com.epam.borysenko.service.OrderService;
import com.epam.borysenko.servlet.AbstractServlet;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.borysenko.constants.ContextConstants.ORDER_SERVICE;
import static com.epam.borysenko.constants.ContextConstants.SHOPPING_CART;

@WebServlet("/json/product/remove")
public class RemoveProductFromShoppingCartServlet extends AbstractServlet {

    private static final Logger REMOVE_PRODUCT_SERVLET_LOGGER = LoggerFactory.getLogger(RemoveProductFromShoppingCartServlet.class);
    private static final String TOTAL_COUNT = "totalCount";
    private static final String TOTAL_COST = "totalCost";
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductForm productForm = createProductForm(req);
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(SHOPPING_CART);
        try {
            orderService.removeProductToShoppingCart(productForm, shoppingCart);
        } catch (ServiceException e) {
            REMOVE_PRODUCT_SERVLET_LOGGER.error(e.getMessage());
        }
        JSONObject json = new JSONObject();
        json.put(TOTAL_COUNT, shoppingCart.getTotalCount());
        json.put(TOTAL_COST, shoppingCart.getTotalCost());
        sentJSON(json, resp);
    }

    private void sentJSON(JSONObject json, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().println(json.toString());
        resp.getWriter().close();
    }
}
