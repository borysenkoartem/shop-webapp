<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-registrationForm">
    <div class="row">
        <h1 class="head">Order details</h1>
        <form class="registrationForm" id="" method="post" action="/order">
            <div class="form-group">
                <label>Phone</label>
                <input type="text" name="phone" class="form-control" placeholder="Phone">
            </div>

            <div class="form-group">
                <label>Address</label>
                <textarea class="form-control" placeholder="Address" name="address" rows="3"></textarea>
            </div>
            <h4>Delivery type</h4>
            <c:forEach items="${deliveries}" var="delivery">

                <div class="form-group pl-4">
                    <input class="form-check-input" name="delivery" type="radio" checked value="${delivery.id}">
                    <label class="form-check-label">
                            ${delivery.name}
                    </label>
                </div>
            </c:forEach>
            <h4>Payment type</h4>
            <c:forEach items="${payments}" var="payment">
                <div class="form-group pl-4">
                    <input class="form-check-input" name="payment" type="radio" checked value="${payment.id}">
                    <label class="form-check-label">
                            ${payment.name}
                    </label>
                </div>
            </c:forEach>
            <button type="submit" value="Submit" class="btn btn-primary">Make order</button>
        </form>
    </div>
</div>