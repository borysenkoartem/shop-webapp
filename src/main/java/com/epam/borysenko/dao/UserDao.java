package com.epam.borysenko.dao;

import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.entity.User;

/**
 * User Data Access Object interface.
 *
 * @author Artem Borysenko
 */
public interface UserDao {

    /**
     * Get the User by login.
     *
     * @param login searching login.
     * @return null if Account does not exist.
     */
    User getUserByLogin(String login) throws DAOException;


    /**
     * Insert User to DB.
     *
     * @param user That we want to add.
     */
    void createUser(User user) throws DAOException;
}
