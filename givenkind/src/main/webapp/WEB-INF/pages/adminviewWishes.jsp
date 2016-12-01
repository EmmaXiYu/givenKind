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
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<title>Wish List</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Create Wish List Item</h1>
				
				<input type='hidden' id='userId' name='userId' value='${userId}' />
				<c:url var="addUrl" value="/adminAddWish" />
					<form:form role="form" modelAttribute='wishlistDTO' name="addWish"
						action="${addUrl}" method='POST' id="addWish">
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name</th>
							<th class='col-sm-2'>Expiration Date</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-3'>Category
							<label style="font-size: 11px;font-weight: bold;">(Limit to 3 categories)</label>
							</th>
							<th class='col-sm-3'>Description</th>
							<th class='col-sm-2'>Impact</th>
						</tr>
					</thead>
					
						<tbody>
							<tr>
								<td><form:input class='form-control' path="itemName"
										id="itemName" /> <form:errors class="error" path="itemName" maxlength='100'/></td>
								<td><form:input class='form-control datepicker'
										path="dateExpires" id="dateExpires"></form:input> <form:errors class="error"
										path="dateExpires" /></td>
								<td><form:input  class='form-control'
										step='any' min='1' path="quantityDesired" id="quantityDesired" maxlength='10'></form:input>
									<form:errors class="error" path="quantityDesired" /></td>
								<td><form:select class='form-control'
										items="${ItemCategoryList}" id='itemCategories'
										path='wishlistItemCategories' multiple="true"
										selected='selected'></form:select> <form:errors
										class="error"
										path="wishlistItemCategories" />
										<span id="selectError" class="error hidden">Only three categories can be selected</span></td>
										
								<td><form:input id='note' type='textarea'
										class='form-control' path='note' placeholder="Description" maxlength='100'></form:input>
									<form:errors path="note" class="error" /></td>
								<td><form:select id='impact' style="width:250px;"
										class='form-control' path='impact' selected='selected' placeholder="Impact" >										
										<option value="Support community programs">Support community programs</option>
										<option value="Support general operations">Support general operations</option>
										</form:select> 
										<form:errors path="impact" class="error" />
										</td> 
										</tr>
														
						</tbody>
									
				</table>

				
				<div style="width:864px;">
				<label style="font-size: 15px;font-weight: bold;"> Please tell us how donations to your wish list will impact your organization and/or community.</label>
				</div>
				<form:textarea style="width:650px;" id='comments' type='textarea' rows ="5" maxlength="255"
										 path='comments' placeholder="Comments"></form:textarea>
				<form:errors path="comments" class="error" /> 
					
				<div style="width:864px;">
				<label style="font-size: 11px;font-weight: bold;"> (all fields required)</label>
				</div>
				<input type="submit" id="submitBtn" class="formbutton"
									value="Add Wish" />	 
									
				 </form:form> 
							

		

				<h1>All Wish List</h1>

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
									href="<c:url value="/adminDeleteWish?wishId=${wishlist.id}" />">Delete</a>
									| <a href="<c:url value="/adminEditWish?wishId=${wishlist.id}" />">Edit</a></td>
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
		$(":input").keyup(function() {
			var itemName = document.getElementById('itemName').value;
			var dateExpires = document.getElementById('dateExpires').value;
			var quantityDesired = document.getElementById('quantityDesired').value;
			var itemCategories = document.getElementById('itemCategories').value;
			var note = document.getElementById('note').value;
			var impact = document.getElementById('impact').value;
			var comments = document.getElementById('comments').value;
			if(!itemName || !dateExpires || !quantityDesired || !itemCategories || !note || !impact || !comments)
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
