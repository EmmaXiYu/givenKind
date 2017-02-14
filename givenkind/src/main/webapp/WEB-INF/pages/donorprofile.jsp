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
<link rel="icon" href="<c:url value="/img/favicon.ico" />" >
<title>Donor Profile</title>
</head>
<body id="grayBackground">
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id="mainContainer" class='container-fluid'>
	<div class='row'>
	    <div class='col-sm-12'>
		    <form:form role='form' class="form-horizontal" action='donor/edit' method='GET'>
		        <div class='col-sm-offset-4 col-sm-8'>
			        <h1><c:out value="${profileDTO.contactPerson}" escapeXml='true'/></h1>
			    </div>
				<div class='form-group'>
					<label for='contactPerson' class='control-label col-sm-4'>Name</label>
					<div class='col-sm-8'>
					    <p class="form-control-static"><c:out value="${profileDTO.contactPerson}" escapeXml='true'/></p>
					</div>
				</div>
				<div class='form-group'>
					<label for='contactEmail' class='control-label col-sm-4'>Email</label>
					<div class='col-sm-8'>
	                    <p class="form-control-static"><c:out value="${profileDTO.contactEmail}" escapeXml='true'/></p>
	                </div>
				</div>
				<div class='form-group'>
					<label for='contactPhone' class='control-label col-sm-4'>Phone</label>
					<div class='col-sm-8'>
	                    <p class="form-control-static"><c:out value="${profileDTO.contactPhone}" escapeXml='true'/></p>
	                </div>
				</div>
				<div class='form-group'>
					<label for='address1' class='control-label col-sm-4'>Address</label>
					<div class='col-sm-8'>
	                    <p class="form-control-static"><c:out value="${profileDTO.address1}" escapeXml='true'/><br/>
	                    <c:out value="${profileDTO.city}" escapeXml='true' />, <c:out value="${profileDTO.state}" escapeXml='true' /> <c:out value="${profileDTO.zip}" escapeXml='true' /></p>
	                </div>
				</div><br/>
				<div class='form-group'>
					<div class='col-sm-offset-4 col-sm-8'>
						<button class='btn btn-default' type="submit">Edit Profile</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>
