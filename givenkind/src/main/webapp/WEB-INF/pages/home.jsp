<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<title>Home</title>
<!--   style>
.btn-lg { font-size: 36px }
</style -->
</head>
<body>

<%@ include file="nav.jsp" %>

<div class='row' id='gnk-slogan-row'>
	<div class='col-sm-offset-4 col-sm-4 text-center'>
			<div class="gnk-slogan">connect. donate. do good.</div>
	</div>
</div>

<div class='row' id='gnk-home-btn-row'>
	<div class='col-sm-offset-3 col-sm-2 text-center'>
		<sec:authorize access="isAnonymous()">
			<a href='NPFhiw' role='button' class='btnNonprofit'>Information for Nonprofits</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_NONPROFIT')">
			<a href='<c:url value="/wishlist"/>' role='button' class='btnNonprofit'>Add/Edit Wishlist</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_DONOR')">
			<a href='<c:url value="/donorlist"/>' role='button' class='btnNonprofit'>Add/Edit Items to Donate</a>
		</sec:authorize>
	</div>
	
	<div class='col-sm-2 text-center'>
		<sec:authorize access="hasRole('ROLE_NONPROFIT')">
			<a href='<c:url value="/npTransactions"/>' role='button' class='btnNonprofit'>View Active Transactions</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_DONOR')">
			<a href='<c:url value="/donorTransactions"/>' role='button' class='btnNonprofit'>View Active Transactions</a>
		</sec:authorize>
	</div>

	<div class='col-sm-2 text-center'>
		<sec:authorize access="isAnonymous()">
			<a href='Dhiw' role='button' class='btnDonor'>Information for Donors</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_NONPROFIT')">
			<a href='<c:url value="/search"/>' role='button' class='btnNonprofit'>Find Available Items</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_DONOR')">
			<a href='<c:url value="/search"/>' role='button' class='btnNonprofit'>Find Desired Items</a>
		</sec:authorize>
	</div>
</div>

<div class='row' id='gnk-home-btn2-row'>
	<div class='col-sm-offset-4 col-sm-4 text-center'>
		
		<sec:authorize access="hasRole('ROLE_NONPROFIT')">
			<a href='<c:url value="/completedNPtxn"/>' role='button' class='btnNonprofit'>View Completed Transactions</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_DONOR')">
			<a href='<c:url value="/completedDonortxn"/>' role='button' class='btnNonprofit'>View Completed Transactions</a>
		</sec:authorize>
	</div>
	
</div>
			
<%@ include file="footer.jsp" %>

</body>
</html>
