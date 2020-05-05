package com.epam.borysenko.servlet.page;

import com.epam.borysenko.entity.order.Delivery;
import com.epam.borysenko.entity.order.Payment;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.entity.ShoppingCart;
import com.epam.borysenko.model.form.OrderForm;
import com.epam.borysenko.service.OrderService;
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
import static com.epam.borysenko.constants.PathConstants.ORDER_PAGE;
import static com.epam.borysenko.constants.PathConstants.PRODUCTS_SERVLET;

@WebServlet("/order")
public class OrderServlet extends AbstractServlet {

    private static final Logger ORDER_SERVLET = LoggerFactory.getLogger(OrderServlet.class);

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(SHOPPING_CART);
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER);
        if (shoppingCart == null || currentUser == null) {
            resp.sendRedirect(PRODUCTS_SERVLET);
        } else {
            try {
                List<Delivery> deliveryList = orderService.getAllDeliveryTypes();
                List<Payment> paymentList = orderService.getAllPaymentTypes();
                req.setAttribute("payments", paymentList);
                req.setAttribute("deliveries", deliveryList);
                forwardToPage(ORDER_PAGE, req, resp);
            } catch (ServiceException e) {
                ORDER_SERVLET.error("Error during doGet");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(SHOPPING_CART);
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER);
        OrderForm orderForm = createOrderForm(req);
        try {
            orderService.makeOrder(shoppingCart, currentUser, orderForm);
        } catch (ServiceException e) {
            ORDER_SERVLET.error("Error during doPost");
        }
        shoppingCart.clear();
        req.getSession().setAttribute(SUCCESS_MASSAGE, "Order created successfully. Please wait for our reply.");
        resp.sendRedirect(PRODUCTS_SERVLET);
    }

    private OrderForm createOrderForm(HttpServletRequest req) {
        return new OrderForm(req.getParameter("phone"),
                req.getParameter("address"),
                Integer.parseInt(req.getParameter("delivery")),
                Integer.parseInt(req.getParameter("payment")));
    }

}
