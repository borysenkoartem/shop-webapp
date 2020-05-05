package com.epam.borysenko.dao;

import com.epam.borysenko.entity.product.Category;
import com.epam.borysenko.entity.product.Producer;
import com.epam.borysenko.entity.product.Product;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.model.SearchQuery;

import java.util.List;

/**
 * Product Data Access Object interface.
 *
 * @author Artem Borysenko
 */
public interface ProductDao {

    /**
     * Get List Products
     *
     * @param searchQuery Search query with parameters and command.
     * @return null if products does not exist.
     * @throws DAOException if was any problem during getting information from DB.
     */
    List<Product> getListProducts(SearchQuery searchQuery) throws DAOException;

    /**
     * Count products that suitable to searchQuery
     *
     * @param searchQuery Search query with parameters and command.
     * @return 0 if no products at Data Base.
     * @throws DAOException if was any problem during getting information from DB.
     */
    int getCountProducts(SearchQuery searchQuery) throws DAOException;

    /**
     * Get List Categories.
     *
     * @return null if no category at Data Base.
     * @throws DAOException if was any problem during getting information from DB.
     */
    List<Category> listAllCategories() throws DAOException;

    /**
     * Get List Producers.
     *
     * @return null if no producer at Data Base.
     * @throws DAOException if was any problem during getting information from DB.
     */
    List<Producer> listAllProducers() throws DAOException;

    Product getProductById(int id) throws DAOException;
}
