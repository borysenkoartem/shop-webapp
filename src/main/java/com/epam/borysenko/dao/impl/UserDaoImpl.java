package com.epam.borysenko.dao.impl;

import com.epam.borysenko.dao.UserDao;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.handler.DefaultResultSetHandler;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.util.connection.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.borysenko.constants.DaoQueryConstant.CREATE_USER;
import static com.epam.borysenko.constants.DaoQueryConstant.GET_USER_BY_LOGIN;


public class UserDaoImpl implements UserDao {

    private QueryRunner queryRunner;
    private DefaultResultSetHandler userResultSetHandler;

    public UserDaoImpl(QueryRunner queryRunner, DefaultResultSetHandler userResultSetHandler) {
        this.userResultSetHandler = userResultSetHandler;
        this.queryRunner = queryRunner;
    }

    @Override
    public User getUserByLogin(final String login) throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            return (User) queryRunner.query(connection, GET_USER_BY_LOGIN, userResultSetHandler, login);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void createUser(final User user) throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            queryRunner.update(connection, CREATE_USER,
                    user.getLogin(), user.getPassword(), user.getEmail(), user.getFirstName(),
                    user.getLastName(), user.getAvatarLink(), user.getNewsletterConsent());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
