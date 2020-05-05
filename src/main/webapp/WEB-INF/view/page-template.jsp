<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="/WEB-INF/tld/MyTag.tld" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>IShop layout</title>
    <link rel="shortcut icon" href="/static/img/bag.ico" type="image/x-icon"/>
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/app.css" rel="stylesheet">
</head>
<body>

<header>
    <jsp:include page="fragment/header.jsp"/>
</header>

<jsp:include page="${currentPage}"/>

<script src="/static/js/jquery.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/search.js"></script>
<script src="/static/js/localisation.js"></script>
<script src="/static/js/addAndRemoveProduct.js"></script>

<!--<script src="static/js/validation.js"></script>-->

<footer class="footer">
    <div class="container text-center"> IShop demo for PreProductionLab, 2020</div>
</footer>
</body>
</html>
