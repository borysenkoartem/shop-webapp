package com.epam.borysenko.service;

import com.epam.borysenko.entity.product.Category;
import com.epam.borysenko.entity.product.Producer;
import com.epam.borysenko.entity.product.Product;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.model.form.SearchForm;

import java.util.List;

public interface ProductService {


    /**
     * Get List Products
     *
     * @param searchForm Search form with parameters and command.
     * @return List products, null if products does not exist.
     * @throws ServiceException if was any problem during getting information from DB.
     */
    List<Product> getListProducts(SearchForm searchForm) throws ServiceException;

    /**
     * Get List Categories.
     *
     * @return null if no category at Data Base.
     * @throws ServiceException if was any problem during getting information from DB.
     */
    List<Category> listAllCategories() throws ServiceException;

    /**
     * Get List Producers.
     *
     * @return null if no producer at Data Base.
     * @throws ServiceException if was any problem during getting information from DB.
     */
    List<Producer> listAllProducer() throws ServiceException;

    /**
     * Count products that suitable to searchQuery
     *
     * @param searchForm form query with parameters and command.
     * @return 0 if no products at Data Base.
     * @throws ServiceException if was any problem during getting information from DB.
     */

    int getCountProducts(SearchForm searchForm) throws ServiceException;

}
