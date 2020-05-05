<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/MyTag.tld" prefix="tag" %>


<c:if test="${CURRENT_USER==null}">
    <div class="alert alert-primary container-fluid text-center" role="alert">
        To make order, please sign in.
    </div>
</c:if>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Count</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <div id="shoppingCart">
    <c:forEach items="${SHOPPING_CART.shoppingCard}" var="shoppingCartEntry">
        <tr id="product${shoppingCartEntry.key.id}" class="item">
            <td class="text-center"><img class="small" src="${shoppingCartEntry.key.imageLink}"
                                         alt="${shoppingCartEntry.key.name}"><br>${shoppingCartEntry.key.name}
            </td>
            <td class="price">$ ${shoppingCartEntry.key.price}</td>
            <td class="count">${shoppingCartEntry.value}</td>

            <c:choose>
                <c:when test="${shoppingCartEntry.value>1}">
                    <td>
                        <a class="btn btn-danger remove-product" data-id-product="${shoppingCartEntry.key.id}"
                           data-count="1">Remove one</a>
                        <a class="btn btn-danger remove-product" data-id-product="${shoppingCartEntry.key.id}"
                           data-count="${shoppingCartEntry.value}">Remove all</a>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <a class="btn btn-danger remove-product" data-id-product="${shoppingCartEntry.key.id}"
                           data-count="1">Remove one</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="2" class="text-right"><strong>Total:</strong></td>
        <td colspan="2" class="total">${SHOPPING_CART.totalCost}</td>
    </tr>
    </tbody>
</table>
<c:choose>
    <c:when test="${CURRENT_USER==null}">
        <div class="container-fluid text-center">
            <tag:avatarTag/>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container-fluid text-center">
            <a class="btn btn-primary" href="/order">Make order</a>
        </div>
    </c:otherwise>
</c:choose>

