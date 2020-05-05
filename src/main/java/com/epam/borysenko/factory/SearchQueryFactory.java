package com.epam.borysenko.factory;

import com.epam.borysenko.model.form.SearchForm;
import com.epam.borysenko.model.SearchQuery;

public interface SearchQueryFactory {

    SearchQuery createQuery(String selectFields, SearchForm searchForm);

    SearchQuery createCountQuery(String selectFields, SearchForm searchForm);

}
