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
<title>NonProfit Profile</title>
</head>
	<body>
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id="mainContainer" class='container-fluid'>
	<div class='row'>
		<h1>Profile of <c:out value="${profileDTO.organizationName}" escapeXml='true'/></h1>
		<form:form role='form' class='form-horizontal' action='edit' method='GET'>
			<div class='form-group'>
				<label for='organizationName' class='control-label col-sm-4'>Organization Name</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.organizationName}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<label for='missionStatement' class='control-label col-sm-4'>Mission Statement</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.missionStatement}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<label for='website' class='control-label col-sm-4'>Website</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.website}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<label for='address1' class='control-label col-sm-4'>Address</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.address1}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<p class='form-control-static col-sm-8 col-sm-offset-4'><c:out value="${profileDTO.city}" escapeXml='true'/>, <c:out value="${profileDTO.state}" escapeXml='true'/> <c:out value="${profileDTO.zip}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<label class='control-label col-sm-4'>Pickup Service</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.pickupService}" escapeXml='true'/></p>
			</div>
			<c:if test="${profileDTO.pickupService eq 'Yes'}">
				<div class='form-group'>
					<label class='control-label col-sm-4'>Pickup Distance</label>
					<p class='form-control-static col-sm-8'><c:out value="${profileDTO.pickupDistance}" escapeXml='true'/></p>
				</div>
			</c:if>
			<div class='form-group'>
				<label for='employerIdentificationNumber' class='control-label col-sm-4'>EIN</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.employerIdentificationNumber}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<label for='nonprofitCategories' class='control-label col-sm-4'>NonProfit Categories<br />(up to three)</label>
				<p class='form-control-static col-sm-8'><c:forEach items="${profileDTO.nonprofitCategories}" var="nonprofitCategories" varStatus="loop">${nonprofitCategories}<c:if test="${!loop.last}">,</c:if></c:forEach></p>
			</div>
			<div class='form-group'>
				<label for='contactPerson' class='control-label col-sm-4'>Contact Person</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.contactPerson}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<label for='contactEmail' class='control-label col-sm-4'>Contact Email</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.contactEmail}" escapeXml='true'/></p>
			</div>
			<div class='form-group'>
				<label for='contactPhone' class='control-label col-sm-4'>Contact Phone</label>
				<p class='form-control-static col-sm-8'><c:out value="${profileDTO.contactPhone}" escapeXml='true'/></p>
			</div>
			 
			<div class='form-group'>
				<div class='col-sm-offset-4 col-sm-8'>
					<button class='btn btn-default' type="submit">Edit Profile</button>
				</div>
			</div>
			 
		</form:form>
	</div>
</div>
<%@ include file="footer.jsp" %>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>
