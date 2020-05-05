package com.epam.borysenko.factory.impl;

import com.epam.borysenko.factory.SearchQueryFactory;
import com.epam.borysenko.model.form.SearchForm;
import com.epam.borysenko.model.SearchQuery;

import java.util.ArrayList;
import java.util.List;

import static com.epam.borysenko.constants.DaoQueryConstant.PRODUCT_PRODUCER_CATEGORY_TABLE;

public class SearchQueryFactoryImpl implements SearchQueryFactory {

    private List<Object> params;
    private StringBuilder sql;

    @Override
    public SearchQuery createQuery(String selectFields, SearchForm searchForm) {
        createCountQuery(selectFields, searchForm);
        addOrderBy(searchForm);
        addLimitOffset(searchForm);
        return new SearchQuery(sql, params);
    }

    public SearchQuery createCountQuery(String selectFields, SearchForm searchForm) {
        params = new ArrayList<>();
        sql = new StringBuilder("SELECT ");
        sql.append(selectFields);
        sql.append(PRODUCT_PRODUCER_CATEGORY_TABLE);
        addQuery(searchForm);
        addCategories(searchForm);
        addProducers(searchForm);
        addMinPrice(searchForm);
        addMaxPrice(searchForm);
        addCategoryURI(searchForm);
        return new SearchQuery(sql, params);
    }

    private void addCategoryURI(SearchForm searchForm) {
        if (searchForm.getCategoryURI() != null) {
            sql.append(" and c.url = ? ");
            params.add(searchForm.getCategoryURI());
        }
    }

    private void addLimitOffset(SearchForm searchForm) {
        sql.append(" limit ? offset ?");
        int offset = (Integer.parseInt(searchForm.getPage()) - 1) * Integer.parseInt(searchForm.getLimitPerPage());
        params.add(Integer.parseInt(searchForm.getLimitPerPage()));
        params.add(offset);
    }

    private void addOrderBy(SearchForm searchForm) {
        sql.append(searchForm.getSort());
    }

    private void addQuery(SearchForm searchForm) {
        if (searchForm.getQuery() != null) {
            sql.append("AND (p.name like ?  OR p.description like ?)");
            params.add("%" + searchForm.getQuery() + "%");
            params.add("%" + searchForm.getQuery() + "%");
        }
    }

    private void addCategories(SearchForm searchForm) {
        if (searchForm.getCategories() != null) {
            addSqlCommandAndParams(sql, params, searchForm.getCategories(), "c.id = ?");
        }
    }

    private void addProducers(SearchForm searchForm) {
        if (searchForm.getProducers() != null) {
            addSqlCommandAndParams(sql, params, searchForm.getProducers(), "pr.id = ?");
        }
    }

    private void addMinPrice(SearchForm searchForm) {
        if (searchForm.getMinPrice() != null
                && !searchForm.getMinPrice().equals("")) {
            sql.append("AND p.price > ?");
            params.add(searchForm.getMinPrice());
        }
    }

    private void addMaxPrice(SearchForm searchForm) {
        if (searchForm.getMaxPrice() != null
                && !searchForm.getMaxPrice().equals("")) {
            sql.append("AND p.price < ?");
            params.add(searchForm.getMaxPrice());
        }
    }

    private void addSqlCommandAndParams(StringBuilder sql, List<Object> queryParams, String[] args, String expression) {

        if (args != null && args.length != 0) {
            sql.append(" and (");
            for (int i = 0; i < args.length; i++) {
                sql.append(expression);
                queryParams.add(args[i]);
                if (i != args.length - 1) {
                    sql.append(" or ");
                }
            }
            sql.append(")");
        }
    }
}
