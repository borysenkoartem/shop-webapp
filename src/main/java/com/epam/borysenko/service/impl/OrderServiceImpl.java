package com.epam.borysenko.service.impl;


import com.epam.borysenko.dao.OrderDao;
import com.epam.borysenko.dao.ProductDao;
import com.epam.borysenko.entity.ShoppingCart;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.entity.order.Delivery;
import com.epam.borysenko.entity.order.Payment;
import com.epam.borysenko.entity.product.Product;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.exception.ValidationException;
import com.epam.borysenko.model.form.OrderForm;
import com.epam.borysenko.model.form.ProductForm;
import com.epam.borysenko.service.OrderService;
import com.epam.borysenko.util.connection.ConnectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private static final int COUNT_OF_INSERT_PARAMETERS = 4;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final DataSource dataSource;

    public OrderServiceImpl(OrderDao orderDao, ProductDao productDao, DataSource dataSource) {
        this.orderDao = orderDao;
        this.dataSource = dataSource;
        this.productDao = productDao;
    }

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            Product product = productDao.getProductById(productForm.getProductId());
            if (product == null) {
                throw new ValidationException("You try to add no exist product");
            } else {
                shoppingCart.addProduct(product, productForm.getCount());
            }
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void removeProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            Product product = productDao.getProductById(productForm.getProductId());
            if (product == null) {
                throw new ValidationException("You try to remove no exist product");
            } else {
                shoppingCart.removeProduct(product, productForm.getCount());
            }
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void makeOrder(ShoppingCart shoppingCart, User user, OrderForm orderForm) throws ServiceException {
        if (shoppingCart == null || shoppingCart.getShoppingCard().isEmpty()) {
            throw new ValidationException("Shopping card is null or empty");
        }
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            String orderUIDD = UUID.randomUUID().toString();
            orderDao.createOrder(orderUIDD, user, orderForm);
            orderDao.insertOrderItem(toOrderItemParameterList(orderUIDD, shoppingCart));
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    private Object[][] toOrderItemParameterList(String orderUIDD, ShoppingCart shoppingCart) {
        Object[][] parameters = new Object[shoppingCart.getShoppingCard().size()][COUNT_OF_INSERT_PARAMETERS];
        List<Object[]> parametersList = new ArrayList<>();
        shoppingCart.getShoppingCard().forEach((product, count) -> parametersList.add(new Object[]{orderUIDD, product.getId(), product.getPrice(), count}));
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parametersList.get(i);
        }
        return parameters;
    }

    @Override
    public List<Delivery> getAllDeliveryTypes() throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            return orderDao.getAllDeliveryTypes();
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Payment> getAllPaymentTypes() throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            return orderDao.getAllPaymentTypes();
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}