package com.epam.borysenko.dao.impl;

import com.epam.borysenko.entity.User;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.handler.DefaultResultSetHandler;
import com.epam.borysenko.util.connection.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.borysenko.constants.DaoQueryConstant.CREATE_USER;
import static com.epam.borysenko.constants.DaoQueryConstant.GET_USER_BY_LOGIN;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

    private static final String LOGIN = "login";
    @Mock
    private QueryRunner queryRunner;
    @Mock
    private Connection connection;
    @Mock
    private User user;
    @Mock
    private DefaultResultSetHandler defaultResultSetHandler;

    @InjectMocks
    UserDaoImpl userDao;

    @Before
    public void setUp() throws Exception {
        ConnectionUtils.setCurrentConnection(connection);
    }

    @Test
    public void testGetUserByLoginMethodWithCorrectQuery() throws SQLException, DAOException {
        when(queryRunner.query(connection, GET_USER_BY_LOGIN, defaultResultSetHandler, LOGIN)).thenReturn(user);
        userDao.getUserByLogin(LOGIN);
        verify(queryRunner).query(connection, GET_USER_BY_LOGIN, defaultResultSetHandler, LOGIN);
    }

    @Test(expected = DAOException.class)
    public void testGetUserByLoginMethodWithoutConnection() throws SQLException, DAOException {
        when(queryRunner.query(connection, GET_USER_BY_LOGIN, defaultResultSetHandler, LOGIN)).thenReturn(SQLException.class);
        userDao.getUserByLogin(LOGIN);
    }

    @Test
    public void testCreateUserMethod() throws SQLException, DAOException {
        userDao.createUser(user);
        verify(queryRunner).update(connection, CREATE_USER,
                user.getLogin(), user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getAvatarLink(), user.getNewsletterConsent());
    }

    @Test(expected = DAOException.class)
    public void testCreateUserMethodWithoutConnection() throws SQLException, DAOException {
        when(queryRunner.update(connection, CREATE_USER,
                user.getLogin(), user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getAvatarLink(), user.getNewsletterConsent())).thenThrow(SQLException.class);
        userDao.createUser(user);
    }
}