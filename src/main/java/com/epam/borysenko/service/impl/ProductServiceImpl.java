package com.epam.borysenko.service.impl;

import com.epam.borysenko.dao.ProductDao;
import com.epam.borysenko.entity.product.Category;
import com.epam.borysenko.entity.product.Producer;
import com.epam.borysenko.entity.product.Product;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.exception.ServiceException;
import com.epam.borysenko.factory.SearchQueryFactory;
import com.epam.borysenko.factory.impl.SearchQueryFactoryImpl;
import com.epam.borysenko.model.SearchQuery;
import com.epam.borysenko.model.form.SearchForm;
import com.epam.borysenko.service.ProductService;
import com.epam.borysenko.util.connection.ConnectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.borysenko.constants.DaoQueryConstant.COUNT_ALL;
import static com.epam.borysenko.constants.DaoQueryConstant.GET_ALL_PRODUCTS;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final DataSource dataSource;
    private SearchQueryFactory searchQueryFactory = new SearchQueryFactoryImpl();

    public ProductServiceImpl(ProductDao productDao, DataSource dataSource) {
        this.productDao = productDao;
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getListProducts(SearchForm searchForm) throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            SearchQuery searchQuery = searchQueryFactory.createQuery(GET_ALL_PRODUCTS, searchForm);
            return productDao.getListProducts(searchQuery);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Category> listAllCategories() throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            return productDao.listAllCategories();
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Producer> listAllProducer() throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            return productDao.listAllProducers();
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public int getCountProducts(SearchForm searchForm) throws ServiceException {
        try (Connection c = dataSource.getConnection()) {
            ConnectionUtils.setCurrentConnection(c);
            SearchQuery searchQuery = searchQueryFactory.createCountQuery(COUNT_ALL, searchForm);
            return productDao.getCountProducts(searchQuery);
        } catch (SQLException | DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
