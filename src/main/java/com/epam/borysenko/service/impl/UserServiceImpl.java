package com.epam.borysenko.service.impl;

import com.epam.borysenko.dao.UserDao;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.entity.User;
import com.epam.borysenko.service.UserService;
import com.epam.borysenko.util.connection.ConnectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final DataSource dataSource;

    public UserServiceImpl(UserDao userDao, DataSource dataSource) {
        this.userDao = userDao;
        this.dataSource = dataSource;
    }

    @Override
    public void createAccount(User user) throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            userDao.createUser(user);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User getAccountByLogin(String login) throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            return userDao.getUserByLogin(login);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
