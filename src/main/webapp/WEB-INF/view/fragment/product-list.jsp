<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty products}">
    <div class="alert alert-primary container-fluid text-center" role="alert">
        No such products.
    </div>
</c:if>

<c:forEach items="${products}" var="product">
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-custom-2">
        <!-- PRODUCT DATA -->
        <div id="product${product.id}" class="card product">
            <div class="card-body">
                <div class="thumbnail">
                    <img src="${product.imageLink}" class="card-img-top"
                         alt="${product.name}">
                    <div class="desc">
                        <div class="cell">
                            <p><span class="title">Details</span>${product.description}</p>
                        </div>
                    </div>
                </div>
                <h4 class="name">${product.name}</h4>
                <div class="code">Code: ${product.id}</div>
                <div class="price" data-price="${product.price}">$ ${product.price}</div>
                <a class="btn btn-primary float-right buy-btn text-white" data-id-product="${product.id}">Buy</a>
                <ul class="list-group">
                    <li class="list-group-item"><small>Category:</small> <span
                            class="category">${product.category}</span>
                    </li>
                    <li class="list-group-item"><small>Producer:</small> <span
                            class="producer">${product.producer}</span>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /PRODUCT DATA -->
    </div>
</c:forEach>


