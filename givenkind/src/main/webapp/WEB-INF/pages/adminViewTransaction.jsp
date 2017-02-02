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
<title>Transaction</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>All Completed Transaction List</h1>

				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Transaction Id</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-2'>Donor</th>
							<th class='col-sm-2'>No Profit</th>
							<th class='col-sm-2'>Donor Item</th>
							<th class='col-sm-2'>Np Wish List</th>
							
						</tr>
					</thead>
					<tbody>
								<c:forEach var="CompletedTransactionItems" items="${CompletedTransactionItems}">
									<tr>
										<td><c:out value="${CompletedTransactionItems.id}" escapeXml='true'/></td>
										<td><c:out value="${CompletedTransactionItems.quantity}" escapeXml='true'/></td>
										<td><a href="<c:url value="/adminViewDonorProfile?donorProfileEmail=${CompletedTransactionItems.donorProfileId.contactEmail}" />">Donor Profile</a></td>
									<td><a href="<c:url value="/adminViewNPProfile?npProfileEmail=${CompletedTransactionItems.npProfileId.contactEmail}" />">NP Profile</a></td>
									<td><a href="<c:url value="/adminViewDonorItem?itemID=${CompletedTransactionItems.donorItemId.id}" />">Donor Items</a></td>
									<td><a href="<c:url value="/adminViewWishItem?itemID=${CompletedTransactionItems.npItemId.id}" />">Wish List Items</a></td>
									</tr>
								</c:forEach>
					</tbody>
				</table>
				
				
				
				<h1>All ActiveTransaction List</h1>

				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Transaction Id</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-2'>Donor</th>
							<th class='col-sm-2'>No Profit</th>
							<th class='col-sm-2'>Donor Item</th>
							<th class='col-sm-2'>Np Wish List</th>
						</tr>
					</thead>
					<tbody>
								<c:forEach var="ActiveTransactionItems" items="${ActiveTransactionItems}">
									<tr>
										<td><c:out value="${ActiveTransactionItems.id}" escapeXml='true'/></td>
										<td><c:out value="${ActiveTransactionItems.quantity}" escapeXml='true'/></td>
										<td><a href="<c:url value="/adminViewDonorProfile?donorProfileEmail=${ActiveTransactionItems.donorProfile.contactEmail}" />">Donor Profile</a></td>
									<td><a href="<c:url value="/adminViewNPProfile?npProfileEmail=${ActiveTransactionItems.npProfile.contactEmail}" />">NP Profile</a></td>
									<td><a href="<c:url value="/adminViewDonorItem?itemID=${ActiveTransactionItems.donorItem.id}" />">Donor Items</a></td>
									<td><a href="<c:url value="/adminViewWishItem?itemID=${ActiveTransactionItems.npItem.id}" />">Wish List Items</a></td>
									</tr>
								</c:forEach>
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
