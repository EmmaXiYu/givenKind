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
<link rel="icon" href="<c:url value="/img/favicon.ico" />" >
<title>Donation List</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Create Donation List</h1>
				<a href="<c:url var="addUrl" value="/donor"/>">Go to Profile</a>
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Type of Item *</th>
							<th class='col-sm-1'>Expiration*</th>
							<th class='col-sm-1'>Quantity *</th>
							<th class='col-sm-3'>Category *</th>
							<th class='col-sm-3'>Description *</th>
							<!-- <th class='col-sm-2'>Impact</th> -->
						<tr>
					</thead>
					<form:form role="form" modelAttribute='wishlistDTO' name="addWish"
						id="addWish">
						<tbody>
							<tr>
								<td><form:input class='form-control' path="itemName" /></td>
								<td><form:input class='form-control datepicker'
										path="dateExpires"></form:input></td>
								<td><form:input type='number' class='form-control'
										step='any' min='1' path="quantityDesired"></form:input></td>
								<td><form:select class='form-control'
										items="${wishlistItemCategoryList}" id='itemCategories'
										path='wishlistItemCategory' selected='selected'></form:select>
								</td>
								<td><form:input id='note' type='textarea'
										class='form-control' path='note' placeholder="Description"></form:input>
								</td>
								<%-- <td><form:input id='impact' type='textarea'
										class='form-control' path='impact' placeholder="Impact" /></td> --%>
							</tr><tr>
								<td><input type="submit" id="submitBtn" class="formbutton"
									value="Add Donation Item" onClick="addWish()" /></td>
							</tr>
						</tbody>
					</form:form>

				</table>
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Name</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-1'>Expiration Date</th>

							<!-- <th class='col-sm-3'>Impact</th> -->
							<th class='col-sm-2'>Description</th>
							<th class='col-sm-2'>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty wishlistDTO.wishlistItems}">
								<c:forEach var="wishlist" items="${wishlistDTO.wishlistItems}">
									<tr>
										<td><c:out value="${wishlist.itemName}" escapeXml='true'/></td>
										<td><c:out value="${wishlist.quantityDesired}" escapeXml='true'/></td>
										<td><fmt:formatDate value='${wishlist.dateExpires}'></fmt:formatDate></td>
										<!-- <td>${wishlist.impact}</td> -->
										<td><c:out value="${wishlist.note}" escapeXml='true'/></td>
										<td><a
											href="/givenkind/deleteDonation?wishId=${wishlist.id}">Delete</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td>No data to show</td>
								</tr>
							</c:otherwise>
						</c:choose>
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
		});
		$(":input").keyup(function() {
			var itemName = document.getElementById('itemName').value;
			var dateExpires = document.getElementById('dateExpires').value;
			var quantityDesired = document.getElementById('quantityDesired').value;
			var itemCategories = document.getElementById('itemCategories').value;
			var note = document.getElementById('note').value;
			if(!itemName || !dateExpires || !quantityDesired || !itemCategories || !note)
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
