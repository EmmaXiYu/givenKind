<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/css/bootstrap-theme.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/js/jquery-ui-1.11.2/jquery-ui.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="icon" href="<c:url value="/img/favicon.ico" />" >
<title>Donor Profile</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Donor Profile</h1>

				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>contactPerson</th>
							<th class='col-sm-1'>contactEmail</th>
							<th class='col-sm-2'>contactPhone</th>
							<th class='col-sm-2'>address1</th>
							<th class='col-sm-2'>address2</th>
							<th class='col-sm-2'>City</th>
							<th class='col-sm-2'>State</th>
							<th class='col-sm-2'>Zip</th>
							
							
						</tr>
					</thead>
					<tbody>
									<tr>
										<td><c:out value="${profile.contactPerson}" escapeXml='true'/></td>
										<td><c:out value="${profile.contactEmail}" escapeXml='true'/></td>
										<td><c:out value="${profile.contactPhone}" escapeXml='true'/></td>
										<td><c:out value="${profile.address1}" escapeXml='true'/></td>
										<td><c:out value="${profile.address2}" escapeXml='true'/></td>
										<td><c:out value="${profile.city}" escapeXml='true'/></td>
										<td><c:out value="${profile.state}" escapeXml='true'/></td>
										<td><c:out value="${profile.zip}" escapeXml='true'/></td>
									
									</tr>
								
					</tbody>
				</table>
				
				
			
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
	<script src="<c:url value="/js/jquery-ui-1.11.2/jquery-ui.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/validation.js" />"></script>
<script src="<c:url value="/js/jquery.mask.min.js" />"></script>

	<script>
	document.getElementById("submitBtn").disabled=true;
	var dateToday = new Date();
		$(function() {
			$(".datepicker").datepicker({
				minDate: dateToday,
			});

			$("#addItemForm").on("submit", function(event) {
				event.preventDefault();
			});
		});
	document.getElementById("submitBtn").disabled=true;
	$(":input").keyup(function() {
		var itemName = document.getElementById('itemName').value;
		var quantityAvailable = document.getElementById('quantityAvailable').value;
		var itemCategories = document.getElementById('itemCategories').value;
		var fairMarketValue = document.getElementById('fairMarketValue').value;
		var description = document.getElementById('description').value;		
		var condition = document.getElementById('condition').value;
		if(!itemName || !dateExpires || !quantityAvailable || !itemCategories || !fairMarketValue || !description ||!condition)
		{
			document.getElementById("submitBtn").disabled=true;
		}
		else{
			document.getElementById("submitBtn").disabled=false;
		}
		});
	</script>
</body>
</html>
