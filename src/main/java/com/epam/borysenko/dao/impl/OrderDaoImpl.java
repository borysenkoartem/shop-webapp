package com.epam.borysenko.dao.impl;

import com.epam.borysenko.dao.OrderDao;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.entity.order.Delivery;
import com.epam.borysenko.entity.order.Payment;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.handler.DefaultResultSetHandler;
import com.epam.borysenko.model.form.OrderForm;
import com.epam.borysenko.util.connection.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.borysenko.constants.DaoQueryConstant.*;


public class OrderDaoImpl implements OrderDao {
    private static final Logger ORDER_DAO_LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

    private QueryRunner queryRunner = new QueryRunner();


    @Override
    @SuppressWarnings("unchecked")
    public List<Delivery> getAllDeliveryTypes() throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            return (List<Delivery>) queryRunner.query(connection, GET_ALL_DELIVERY,
                    new DefaultResultSetHandler(Delivery.class, true));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Payment> getAllPaymentTypes() throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            return (List<Payment>) queryRunner.query(connection, GET_ALL_PAYMENT,
                    new DefaultResultSetHandler(Payment.class, true));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void createOrder(String orderId, User user, OrderForm orderForm) throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            queryRunner.update(connection, CREATE_ORDER,
                    orderId, orderForm.getAddress(), orderForm.getPhone(), orderForm.getDeliveryId(), orderForm.getPaymentId(),
                    user.getId());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void insertOrderItem(Object[][] param) throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            queryRunner.batch(connection, CREATE_ORDER_ITEM, param);
        } catch (SQLException e) {
            ORDER_DAO_LOGGER.error("Error during adding order to DB");
            throw new DAOException(e);
        }
    }
}
