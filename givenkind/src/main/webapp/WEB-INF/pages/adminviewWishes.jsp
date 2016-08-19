<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
<title>Wish List</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Create Wish List Item</h1>
				<h4>(all fields required)</h4>
				<a href="<c:url var="addUrl" value="/nonprofit"/>">Go to Profile</a>
				<input type='hidden' id='userId' name='userId' value='${userId}' />
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name</th>
							<th class='col-sm-2'>Expiration Date</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-3'>Category</th>
							<th class='col-sm-3'>Description</th>
							<th class='col-sm-2'>Community Impact</th>
						</tr>
					</thead>
					<c:url var="addUrl" value="/addWish" />
					<form:form role="form" modelAttribute='wishlistDTO' name="addWish"
						action="${addUrl}" method='POST' id="addWish">
						<tbody>
							<tr>
								<td><form:input class='form-control' path="itemName"
										id="itemName" /> <form:errors class="error" path="itemName" /></td>
								<td><form:input class='form-control datepicker'
										path="dateExpires" id="dateExpires"></form:input> <form:errors class="error"
										path="dateExpires" /></td>
								<td><form:input type='number' class='form-control'
										step='any' min='1' path="quantityDesired" id="quantityDesired"></form:input>
									<form:errors class="error" path="quantityDesired" /></td>
								<td><form:select class='form-control'
										items="${ItemCategoryList}" id='itemCategories'
										path='wishlistItemCategories' multiple="true"
										selected='selected'></form:select> <form:errors
										class="error"
										path="wishlistItemCategories" /></td>
								<td><form:input id='note' type='textarea'
										class='form-control' path='note' placeholder="Description"></form:input>
									<form:errors path="note" class="error" /></td>
								<td><form:input id='impact' type='textarea'
										class='form-control' path='impact' placeholder="Impact" /> <form:errors
										path="impact" class="error" /></td>
							</tr>
							<tr>
								<td><input type="submit" id="submitBtn" class="formbutton"
									value="Add Wish" /></td>
							</tr>
						</tbody>
					</form:form>

				</table>

				<h1>Your Wish List</h1>

				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name</th>
							<th class='col-sm-2'>Expiration Date</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-2'>Description</th>
							<th class='col-sm-3'>Impact</th>
							<th class='col-sm-2'>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="wishlist" items="${wishlistItems}">
							<tr>

								<td><c:out value="${wishlist.itemName}" escapeXml='true' /></td>
								<td><fmt:formatDate pattern="MM/dd/yyyy"
										value='${wishlist.dateExpires}'></fmt:formatDate></td>
								<td><c:out value="${wishlist.quantityDesired}"
										escapeXml='true' /></td>
								<td><c:out value="${wishlist.note}" escapeXml='true' /></td>
								<td><c:out value="${wishlist.impact}" escapeXml='true' /></td>
								<td><a
									href="<c:url value="/deleteWish?wishId=${wishlist.id}" />">Delete</a>
									| <a href="<c:url value="/editWish?wishId=${wishlist.id}" />">Edit</a></td>
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
	<script>
	document.getElementById("submitBtn").disabled=true;
		$(function() {
			$(".datepicker").datepicker();

			$("#addItemForm").on("submit", function(event) {
				event.preventDefault();
			});
		});
		$(":input").keyup(function() {
			var itemName = document.getElementById('itemName').value;
			var dateExpires = document.getElementById('dateExpires').value;
			var quantityDesired = document.getElementById('quantityDesired').value;
			var itemCategories = document.getElementById('itemCategories').value;
			var note = document.getElementById('note').value;
			var impact = document.getElementById('impact').value;
			if(!itemName || !dateExpires || !quantityDesired || !itemCategories || !note || !impact)
			{
				document.getElementById("submitBtn").disabled=true;
			} 
			else {
				document.getElementById("submitBtn").disabled=false;
			}
		});
	</script>
</body>
</html>
