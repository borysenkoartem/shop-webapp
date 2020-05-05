<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-block d-sm-none xs-option-container">
    <a class="dropdown-toggle float-right" data-toggle="collapse" href="#productCatalog">Product catalog</a>
    <a class="dropdown-toggle" data-toggle="collapse" href="#findProducts">Find products</a>
</div>

<form class="search" action="/products">
    <div id="findProducts" class="card text-white bg-dark collapse">
        <div class="card-header">Find products</div>
        <div class="card-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control mr-sm-2" placeholder="Search query"
                       value="${searchForm.query }">
                <span class="input-group-btn">
                <a id="goSearch" class="btn btn-outline-light">Go!</a>
                    </span>
            </div>
            <div class="more-options">
                <a class="dropdown-toggle" data-toggle="collapse" href="#searchOptions">More filters</a>
            </div>
        </div>
        <div id="searchOptions"
                <c:choose>
                    <c:when test="${searchForm!=null}">
                        class="collapse ${!searchForm.categoriesEmpty or !searchForm.producersEmpty ? 'show' : ''}">
                    </c:when>
                    <c:otherwise>
                        class= "collapse">
                    </c:otherwise>
                </c:choose>
            <div class="card-header">Category filters</div>
            <div class="categories">
                <div class="list-group-item text-dark">
                    <div class="checkbox">
                        <label> <input type="checkbox" id="allCategories"> All </label>
                    </div>
                </div>
                <c:forEach items="${categories}" var="category">
                    <div class="list-group-item text-dark">
                        <div class="checkbox">
                            <label><input type="checkbox"
                            <c:forEach items="${searchForm.categories}" var="oneCategory">
                            <c:choose>
                                          <c:when test="${oneCategory==category.id}">checked</c:when>
                            </c:choose>
                            </c:forEach>
                                          name="category" value="${category.id}"
                                          class="search-option"> ${category.name}
                            </label>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="card-header">Producers filters</div>
            <div class="producers">
                <div class="list-group-item text-dark">
                    <div class="checkbox">
                        <label> <input type="checkbox" id="allProducers"> All </label>
                    </div>
                </div>
                <c:forEach items="${producers}" var="producer">
                    <div class="list-group-item text-dark">
                        <div class="checkbox">
                            <label><input type="checkbox"
                            <c:forEach items="${searchForm.producers}" var="oneProducer">
                            <c:choose>
                                          <c:when test="${oneProducer==producer.id}">checked</c:when>
                            </c:choose>
                            </c:forEach>
                                          name="producer" value="${producer.id}"
                                          class="search-option"> ${producer.name}
                            </label>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="card-header">Price range</div>
            <div class="card-body">
                <div class="priceRange">
                    <div class="form-group">
                        <input class="price mb-1" type="text" name="minPrice" placeholder="Min price" value="">
                    </div>
                    <div class="form-group">
                        <input class="price" type="text" name="maxPrice" placeholder="Max price">
                    </div>
                </div>

            </div>
        </div>
    </div>
</form>

<div id="productCatalog" class="card mt-3 text-white bg-dark collapse">
    <div class="card-header">Product catalog</div>
    <div class="list-group">
        <c:forEach items="${categories}" var="category">
            <a href="/products?category=${category.id}" class="list-group-item">${category.name}</a>
        </c:forEach>
    </div>
</div>
<!-- /Categories -->