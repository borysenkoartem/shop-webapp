<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/MyTag.tld" prefix="tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="localisation"/>


<div class="form-registrationForm">
    <div class="row">
        <h1 class="head"><fmt:message key="registrationPage.registrationForm"/></h1>
        <form id="registrationForm" method="post" action="/registration" enctype="multipart/form-data"
              onsubmit="javascript:return validateEverything()">
            <div class="form-group">
                <label for="login"><fmt:message key="registrationPage.login"/></label>
                <input type="login" name="login" id="login" class="form-control"
                       value="${REGISTRATION_FORM.login}">
                <div class="errorMessage">${ERRORS.LOGIN_ERROR}</div>
            </div>

            <div class="form-group">
                <label for="firstName"><fmt:message key="registrationPage.firstName"/></label>
                <input type="firstName" name="firstName" id="firstName" class="form-control"
                       value="${REGISTRATION_FORM.firstName}">
                <div class="errorMessage">${ERRORS.FIRST_NAME_ERROR}</div>
            </div>

            <div class="form-group">
                <label for="email"><fmt:message key="registrationPage.lastName"/></label>
                <input type="lastName" name="lastName" id="lastName" class="form-control"
                       value="${REGISTRATION_FORM.lastName}">
                <div class="errorMessage">${ERRORS.LAST_NAME_ERROR}</div>
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="registrationPage.email"/></label>
                <input type="email" name="email" id="email" class="form-control"
                       value="${REGISTRATION_FORM.email}">
                <div class="errorMessage">${ERRORS.EMAIL_ERROR}</div>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="registrationPage.password"/></label>
                <input type="password" name="password" id="password" class="form-control">
                <div class="errorMessage">${ERRORS.PASSWORD_ERROR}</div>
            </div>
            <div class="form-group">
                <label for="confirmPassword"><fmt:message key="registrationPage.passwordConfirm"/></label>
                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control">
                <div class="errorMessage">${ERRORS.CONFIRM_PASSWORD_ERROR}</div>
            </div>
            <div class = "form-group">
                  <label for="avatar"><fmt:message key="registrationPage.avatar"/></label>
                  <input name="avatar" id ="avatar" accept="image/*" type="file"><br>
                  <div class="errorMessage">${ERRORS.AVATAR_ERROR}</div>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" name="newsletterConsent" id="newsletterConsent">
                <label class="form-check-label" for="newsletterConsent"><fmt:message key="registrationPage.newsletterConsent"/></label>
            </div>
            <tag:captchaTag/>
            <div class="form-group form-check">
                <input type="text" name="captcha" id="captcha" class="form-control">
                <div class="errorMessage">${ERRORS.CAPTCHA_ERROR}</div>
            </div>
            <button type="submit" value="Submit" class="btn btn-primary"><fmt:message key="registrationPage.register"/></button>
        </form>
    </div>
</div>