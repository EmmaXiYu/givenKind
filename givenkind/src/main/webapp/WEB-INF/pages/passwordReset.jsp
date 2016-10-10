<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<title>Reset Password</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id="mainContainer" class='container-fluid'>
    <div class='row'>
        <div class='col-sm-offset-3 col-sm-6'>
        <div class='col-sm-offset-4 col-sm-8'>
            <h1>Forgot Password</h1>
        </div>
        <c:if test="${not empty error}">
            <div class="err col-sm-12">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="msg col-sm-12">${message}</div>
        </c:if>
        <c:if test="${empty msg}">
         <c:url var="addUrl" value="/resetPassword" />
	        <form:form role='form' commandName="passwordResetDTO" class='form-horizontal' action="${addUrl}" method='post'>
	            <div class='form-group'>
	                <label for='email' class='control-label col-sm-4'>New Password</label>
	                <div class='col-sm-8'>
	                    <form:input path="newPassword" type='text' id='newPassword' name='newPassword' class='form-control'></form:input>
	                    <form:errors path="newPassword" class="error" />
	                </div>
	            </div>
	            <form:input path="uniqueResetKey" type="hidden" id="uniqueResetKey" name="uniqueResetKey" />
	            <form:input path="passwordResetAuthorizationId" id="passwordResetAuthorizationId" type="hidden" name="passwordResetAuthorizationId" />
<%-- 	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
	            <div class='form-group'>
	                <div class='col-sm-offset-4 col-sm-8'>
	                    <button class='btn btn-default' type="submit">Submit</button>
	                </div>
	            </div>            
	        </form:form>
        </c:if>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>