<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if  test="${AUTHORISATION_ERROR!=null}" >
    <div class="alert alert-success text-center" role="alert">
            ${AUTHORISATION_ERROR}
    </div>
</c:if>
<form class="form-signIn" id="form-signIn" method="post" action=""
      onsubmit="javascript:return test()">
    <div class="test-center mb-4">
        <h1 class="head">Please Sign In</h1>
    </div>
    <div class="form-label-group">
        <label for="email">Email</label>
        <input type="emailtest" name="login" id="email" class="form-control" placeholder="Email address">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" id="password" class="form-control" placeholder="Password">
        <div class="errorMessage">${LOGIN_ERROR}</div>
    </div>
    <button type="submit" value="Submit" class="btn btn-primary">Sing In</button>
    <a href="/registration"> Registration</a>

</form>

