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
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="icon" href="<c:url value="/img/favicon.ico" />" >
<title>Forgot Password</title>
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
        <c:if test="${not empty msg}">
            <div class="msg col-sm-12">${msg}</div>
        </c:if>
        <c:if test="${empty msg}">
        <c:url var="addUrl" value="/forgot" />
        	<form:form role='form' class='form-horizontal' action="${addUrl}" method='post' commandName='forgotPasswordDTO'>
	            <div class='form-group'>
	                <label for='email' class='control-label col-sm-4'>Email address</label>
	                <div class='col-sm-8'>
	                    <form:input path="email" type='text' id='email' name='email' class='form-control'></form:input>
	                    <form:errors path="email" class="error" />
	                </div>
	            </div>
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