<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/WEB-INF/tld/MyTag.tld" prefix="tag" %>

<nav class="navbar navbar-expand navbar-dark bg-dark head-position">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/products">Products<span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <tag:localisationTag lang="EN"/>
        <tag:localisationTag lang="UK"/>
        <tag:localisationTag lang="RU"/>

        <ul id="currentShoppingCart"
            class="pr-2 nav navbar-nav navbar-right ${SHOPPING_CART.totalCount>0 ? '':'d-none'}">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">
                    <i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping cart (<span
                        class="total-count">${SHOPPING_CART.totalCount}</span>)<span class="caret"></span>
                </a>
                <div class="dropdown-menu shopping-cart-desc">
                    Total count: <span class="total-count">${SHOPPING_CART.totalCount}</span><br>
                    Total cost: <span class="total-cost">${SHOPPING_CART.totalCost}</span><br>
                    <a href="/shopping-cart" class="btn btn-primary btn-block">View cart</a>
                </div>
            </li>
        </ul>
        <tag:avatarTag/>
    </div>
</nav>