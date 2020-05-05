<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <div class="row">
        <aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
            <jsp:include page="fragment/aside.jsp"/>
        </aside>
        <main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">

            <div id="shoppingCart" class="container-fluid">
                <div class="row">
                    <jsp:include page="fragment/shopping-cart-list.jsp"/>
                </div>
            </div>
        </main>
    </div>
</div>

