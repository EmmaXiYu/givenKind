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
<title>Login</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id="mainContainer" class='container-fluid'>
	<div class='row'>
		<div class='col-sm-offset-3 col-sm-6'>
		<h1>Login</h1>
		<c:if test="${not empty error}">
            <div class="error col-sm-12">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg col-sm-12">${msg}</div>
        </c:if>
		<form role='form' class='form-horizontal' action="<c:url value='/login' />" method='post' name='loginForm'>
			<div class='form-group'>
				<label for='email' class='control-label col-sm-4'>User ID/Email</label>
				<div class='col-sm-8'>
					<input type='text' id='username' name='username' class='form-control' autofocus ></input>
				</div>
			</div>
			<div class='form-group'>
				<label for='password' class='control-label col-sm-4'>Password</label>
				<div class='col-sm-8'>
					<input type='password' id='password' name='password' class='form-control'></input>
				</div>
			</div>
			<div class='form-group'>
				<div class='col-sm-offset-4 col-sm-8'>
					<button class='btn btn-default' type="submit">Go!</button>
				</div>
			</div>
			<div>Forgot Password? <a href="<c:url value="/forgot"/>">Reset Password</a></div>
			<br/>
			<br/>
			<p>Need an account? Register here: <a href="register">Non-profits</a> or <a href="registerDonor">Donors</a></p>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
