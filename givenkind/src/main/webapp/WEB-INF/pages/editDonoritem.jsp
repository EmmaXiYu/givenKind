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
<title>Edit Wishlist Item</title>
</head>
<body>
		<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Edit Donor List Item</h1>
				<h4>(all fields required)</h4>
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name </th>
							<th class='col-sm-2'>Expiration Date </th>
							<th class='col-sm-1'>Quantity </th>
							<th class='col-sm-3'>Category </th>
							<th class='col-sm-1'>Condition </th>
							<th class='col-sm-1'>Fair Market Value </th>
							<th class='col-sm-3'>Description</th>
						</tr>
					</thead>
					<c:url var="addUrl" value="/completeEditDonor?donorId=${donorlistDTO.id}&dateExpires=${donorlistDTO.dateExpires}"/>
						<form:form role="form" modelAttribute='donorlistDTO' name="editDonoritem" action="${addUrl}" method='POST' id="editDonoritem">
						<tbody>
							<tr>
								<td><form:input id='itemName' class='form-control' path="itemName" value="${donorlistDTO.itemName}"/></td>
								<td><form:input class='form-control datepicker'
										path="dateExpires" id="dateExpires" value="${donorlistDTO.dateExpires}"></form:input></td>
								<td><form:input id='quantityAvailable' type='number' class='form-control'
										step='1' min='1' path="quantity" value="${donorlistDTO.quantity}"></form:input></td>
								<td><form:select class='form-control'
										items="${ItemCategoryList}" id='itemCategories'
										path='itemCategories' selected='selected' value="${donorlistDTO.itemCategories}"></form:select>
								</td>
								<td><form:input id='condition' class='form-control' path="condition" value="${donorlistDTO.condition}"/></td>
								<td><form:input id='fairMarketValue' type='number' class='form-control'
										 path="fairMarketValue" min='0' step='0.01' value = "${donorlistDTO.fairMarketValue}"></form:input></td>
								<td><form:input id='description' type='textarea'
										class='form-control' path='description' value="${donorlistDTO.description}"></form:input>
								</td>
							</tr>
							<tr>
								<td><input type="submit" id="submitBtn" class="formbutton"
									value="Edit Donor Item"/></td>
							</tr>
						</tbody>
						<a href='<c:url value="/donorlist"/>' role='button' class='button'>Return to Donorlist</a>
					</form:form>
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
		document.getElementById("submitBtn").disabled=true;
		$(":input").keyup(function() {
			var itemName = document.getElementById('itemName').value;
			var quantityAvailable = document.getElementById('quantityAvailable').value;
			var itemCategories = document.getElementById('itemCategories').value;
			var fairMarketValue = document.getElementById('fairMarketValue').value;
			var description = document.getElementById('description').value;
			if(!itemName || !dateExpires || !quantityAvailable || !itemCategories || !fairMarketValue || !description)
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
