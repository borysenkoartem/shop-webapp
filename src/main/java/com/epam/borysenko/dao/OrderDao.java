package com.epam.borysenko.dao;

import com.epam.borysenko.entity.User;
import com.epam.borysenko.entity.order.Delivery;
import com.epam.borysenko.entity.order.Payment;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.model.form.OrderForm;

import java.util.List;

/**
 * Order Data Access Object interface.
 *
 * @author Artem Borysenko
 */

public interface OrderDao {


    /**
     * Get List Delivery types.
     *
     * @return null if no category at Data Base.
     * @throws DAOException if was any problem during getting information from DB.
     */

    List<Delivery> getAllDeliveryTypes() throws DAOException;

    /**
     * Get List Payment types.
     *
     * @return null if no category at Data Base.
     * @throws DAOException if was any problem during getting information from DB.
     */
    List<Payment> getAllPaymentTypes() throws DAOException;


    /**
     * Create order at data base.
     *
     * @param orderId   uniq order id.
     * @param user      user to whom this order will belong.
     * @param orderForm form that have all necessary information.
     * @throws DAOException if was any problem during getting information from DB.
     */
    void createOrder(String orderId, User user, OrderForm orderForm) throws DAOException;


    /**
     * Insert to data base Order Item information.
     *
     * @param param Array with order item information.
     * @throws DAOException if was any problem during getting information from DB.
     */
    void insertOrderItem(Object[][] param) throws DAOException;
}

