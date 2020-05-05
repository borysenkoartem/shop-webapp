package com.epam.borysenko.service;

import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.entity.User;

/**
 * User Service interface.
 *
 * @author Artem Borysenko.
 */
public interface UserService {


    /**
     * Insert User to DB.
     * @param user insert user to DB.
     */
    void createAccount(User user) throws ServiceException;


    /**
     * Get the Account by login.
     *
     * @param login searching login.
     * @return null, if Account does not exist.
     */
    User getAccountByLogin(String login) throws ServiceException;

}
