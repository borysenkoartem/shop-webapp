package com.epam.borysenko.model.form;

import java.util.Objects;

public class SearchForm {

    private String query;
    private String[] categories;
    private String[] producers;
    private String page;
    private String limitPerPage;
    private String minPrice;
    private String maxPrice;
    private String sort;
    private String categoryURI;

    public SearchForm(String query, String[] categories, String[] producers,
                      String page, String limitPerPage, String minPrice,
                      String maxPrice, String sort) {
        this.query = query;
        this.categories = categories;
        this.producers = producers;
        this.page = page;
        this.limitPerPage = limitPerPage;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sort = sort;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getProducers() {
        return producers;
    }

    public void setProducers(String[] producers) {
        this.producers = producers;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimitPerPage() {
        return limitPerPage;
    }

    public void setLimitPerPage(String limitPerPage) {
        this.limitPerPage = limitPerPage;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCategoryURI() {
        return categoryURI;
    }

    public void setCategoryURI(String categoryURI) {
        this.categoryURI = categoryURI;
    }

    public boolean isCategoriesEmpty() {
        return Objects.isNull(categories) || categories.length == 0;
    }

    public boolean isProducersEmpty() {
        return Objects.isNull(producers) || producers.length == 0;
    }
}