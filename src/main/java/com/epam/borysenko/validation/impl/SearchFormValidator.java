package com.epam.borysenko.validation.impl;

import com.epam.borysenko.model.form.SearchForm;
import com.epam.borysenko.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.epam.borysenko.constants.DaoQueryConstant.*;
import static com.epam.borysenko.constants.ValidationConstants.*;


public class SearchFormValidator implements Validator<SearchForm> {


    private static final String DEFAULT_PAGE = "1";
    private static final String DEFAULT_LIMIT_PER_PAGE = "12";
    private Map<String, String> orderByMap;

    public SearchFormValidator() {
        orderByMap = new HashMap<>();
        orderByMap.put(ORDER_BY_NAME_ASC, ORDER_BY_NAME_ASC_COMMAND);
        orderByMap.put(ORDER_BY_NAME_DESC, ORDER_BY_NAME_DESC_COMMAND);
        orderByMap.put(ORDER_BY_PRICE_ASC, ORDER_BY_PRICE_ASC_COMMAND);
        orderByMap.put(ORDER_BY_PRICE_DESC, ORDER_BY_PRICE_DESC_COMMAND);
    }

    @Override
    public Map<String, String> validate(SearchForm searchForm) {
        Map<String, String> errors;
        validatePage(searchForm);
        validateLimitPerPage(searchForm);
        validateSortBy(searchForm);
        errors = validatePrice(searchForm);
        return errors;
    }

    private void validateSortBy(SearchForm searchForm) {
        if (orderByMap.get(searchForm.getSort()) == null) {
            searchForm.setSort(ORDER_BY_NAME_ASC_COMMAND);
        } else {
            searchForm.setSort(orderByMap.get(searchForm.getSort()));
        }
    }

    private void validateLimitPerPage(SearchForm searchForm) {
        try {
            Integer.parseInt(searchForm.getLimitPerPage());
        } catch (NumberFormatException e) {
            searchForm.setLimitPerPage(DEFAULT_LIMIT_PER_PAGE);
        }
    }

    private void validatePage(SearchForm searchForm) {
        try {
            Integer.parseInt(searchForm.getPage());
        } catch (NumberFormatException e) {
            searchForm.setPage(DEFAULT_PAGE);
        }
    }

    private Map<String, String> validatePrice(SearchForm searchForm) {
        Map<String, String> errors = new HashMap<>();
        BigDecimal minPrice;
        BigDecimal maxPrice;

        if (StringUtils.isEmpty(searchForm.getMinPrice()) &&
                StringUtils.isEmpty(searchForm.getMaxPrice())) {
            return errors;
        }
        if (!StringUtils.isEmpty(searchForm.getMaxPrice()) &&
                !NumberUtils.isCreatable(searchForm.getMaxPrice())) {
            errors.put(WRONG_PRICE, WRONG_PRICE_FORMAT_TEXT);
        } else if (!StringUtils.isEmpty(searchForm.getMinPrice()) &&
                !NumberUtils.isCreatable(searchForm.getMinPrice())) {
            errors.put(WRONG_PRICE, WRONG_PRICE_FORMAT_TEXT);
        } else if (NumberUtils.isCreatable(searchForm.getMinPrice())
                && NumberUtils.isCreatable(searchForm.getMaxPrice())) {
            minPrice = new BigDecimal(searchForm.getMinPrice());
            maxPrice = new BigDecimal(searchForm.getMaxPrice());
            if (minPrice.compareTo(maxPrice) > 0) {
                errors.put(WRONG_PRICE, WRONG_PRICE_VALUE_TEXT);
            }
        }
        return errors;
    }
}