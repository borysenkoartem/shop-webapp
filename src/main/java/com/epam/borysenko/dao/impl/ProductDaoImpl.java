package com.epam.borysenko.dao.impl;

import com.epam.borysenko.dao.ProductDao;
import com.epam.borysenko.entity.product.Category;
import com.epam.borysenko.entity.product.Producer;
import com.epam.borysenko.entity.product.Product;
import com.epam.borysenko.exception.DAOException;
import com.epam.borysenko.handler.DefaultResultSetHandler;
import com.epam.borysenko.model.SearchQuery;
import com.epam.borysenko.util.connection.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

import static com.epam.borysenko.constants.DaoQueryConstant.*;


public class ProductDaoImpl implements ProductDao {

    private QueryRunner queryRunner = new QueryRunner();

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getListProducts(SearchQuery searchQuery) throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            return (List<Product>) queryRunner.query(connection, searchQuery.getSql().toString(),
                    new DefaultResultSetHandler(Product.class, true), searchQuery.getParams().toArray());
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int getCountProducts(SearchQuery searchQuery) throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            return Math.toIntExact(queryRunner.query(connection, searchQuery.getSql().toString(),
                    new ScalarHandler<>(1), searchQuery.getParams().toArray()));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> listAllCategories() throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            return (List<Category>) queryRunner.query(connection, GET_ALL_CATEGORY,
                    new DefaultResultSetHandler(Category.class, true));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Producer> listAllProducers() throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
            return (List<Producer>) queryRunner.query(connection, GET_ALL_PRODUCER,
                    new DefaultResultSetHandler(Producer.class, true));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Product getProductById(int id) throws DAOException {
        try {
            Connection connection = ConnectionUtils.getCurrentConnection();
                return (Product) queryRunner.query(connection, GET_PRODUCT,
                        new DefaultResultSetHandler(Product.class, false), String.valueOf(id));
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}