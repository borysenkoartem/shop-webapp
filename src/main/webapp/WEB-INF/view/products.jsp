<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <div class="row">
        <aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
            <jsp:include page="fragment/aside.jsp"/>
        </aside>
        <main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
            <c:if test="${ERRORS.WRONG_PRICE!=null}">
                <div class="alert alert-danger" role="alert">
                        ${ERRORS.WRONG_PRICE}
                </div>
            </c:if>

            <c:if test="${SUCCESS_MASSAGE!=null}">
                <div class="alert alert-success" role="alert">
                        ${SUCCESS_MASSAGE}
                </div>
            </c:if>
            <div class="container-fluid">
                <div class=" xs-option-container">
                    <a class="dropdown-toggle float-left" data-toggle="collapse" href="#sortBy">Sort</a>
                    <a class="dropdown-toggle float-right" data-toggle="collapse" href="#limitCount">Product count</a>
                </div>
            </div>
            <div class="container-fluid float-left">
                <div id="sortBy" class="collapse ">
                    <button type="button" class="btn orderBy btn-dark " value="sort=nameASC">Name A-Z</button>
                    <button type="button" class="btn orderBy btn-dark " value="sort=nameDESC">Name Z-A</button>
                    <button type="button" class="btn orderBy btn-dark " value="sort=priceASC">Price low first</button>
                    <button type="button" class="btn orderBy btn-dark " value="sort=priceDESC">Price high first</button>
                </div>
            </div>
            <div class="container-fluid float-right">
                <div id="limitCount" class="collapse float-right">
                    <button type="button" class="btn limit btn-dark " value="6">6</button>
                    <button type="button" class="btn limit btn-dark " value="12">12</button>
                    <button type="button" class="btn limit btn-dark " value="18">18</button>
                    <button type="button" class="btn limit btn-dark " value="24">24</button>
                </div>
            </div>
            <div id="productList" class="container-fluid">
                <div class="row">
                    <jsp:include page="fragment/product-list.jsp"/>
                </div>
            </div>

            <div id="addProductPopup" class="modal fade" tabindex="-1" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Add product to Shopping cart</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body row">
                            <div class="col-xs-12 col-sm-6">
                                <div class="thumbnail">
                                    <img class="img-thumbnail border-0 product-image"
                                         src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs="
                                         alt="Product image">
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <h4 class="name text-center">Name</h4>
                                <div class="list-group d-none d-sm-block mb-3">
                                    <span class="list-group-item"> <small>Category:</small> <span
                                            class="category">?</span></span>
                                    <span class="list-group-item"> <small>Producer:</small> <span
                                            class="producer">?</span></span>
                                </div>
                                <div class="list-group">
                                    <span class="list-group-item"> <small>Price:</small> <span
                                            class="price">0</span></span>
                                    <span class="list-group-item"> <small>Count:</small> <input type="number"
                                                                                                class="count" value="1"
                                                                                                min="1" max="10"></span>
                                    <span class="list-group-item"> <small>Cost:</small> <span
                                            class="cost">0</span></span>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="addToCart" type="button" class="btn btn-primary">Add to Cart</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${pageCount>1}">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <c:forEach var="i" begin="1" end="${pageCount}">
                            <li class="page-item">
                                <button id="pageNumber" value="${i}" class="page-link">${i}</button>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </main>
    </div>
</div>