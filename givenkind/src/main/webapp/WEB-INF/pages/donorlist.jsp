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
				<h1>Create Donation List Item</h1>
				
				<a href="<c:url value="/donor"/>">Go to Profile</a> 
<!-- 				<input -->
<!-- 					type='hidden' id='profileId' name='profileId' -->
<%-- 					value='${donorlistDTO.profileId}' /> --%>
					<c:url var="addUrl" value="/addToDonorlist"/>
					<form:form role="form" modelAttribute='donorlistDTO' name="addToDonorlist" action="${addUrl}" method='POST' id="addToDonorlist">
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name</th>
							<th class='col-sm-2'>Expiration Date</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-3'>Category
							<span>&nbsp;</span>
							<span style="font-size: 11px;font-weight: bold;">(Limit to 3 categories)</span>
							</th>
							<th class='col-sm-1'>Condition</th>
							<th class='col-sm-1'>Fair Market Value (USD)</th>
							<th class='col-sm-3'>Description</th>
						<tr>
					</thead>
					<%-- <c:url var="addUrl" value="/addToDonorlist"/>
					<form:form role="form" modelAttribute='donorlistDTO' name="addToDonorlist" action="${addUrl}" method='POST' id="addToDonorlist"> --%>
						<tbody>
							<tr>
								<td><form:input id='itemName' class='form-control' path="itemName" placeholder="Item Name"/></td>
								<td><form:input class='form-control datepicker'
										path="dateExpires" id="dateExpires" placeholder="Expiration Date"></form:input></td>
								<td><form:input id='quantityAvailable' type='number' class='form-control'
										step='1' min='1' path="quantity"></form:input></td>
								<td><form:select class='form-control'
										items="${ItemCategoryList}" id='itemCategories'
										path='itemCategories' multiple = "true" selected='selected'></form:select>
								</td>
								<%-- <td><form:input id='condition' class='form-control' path="condition" placeholder="Condition"/></td> --%>
								
								<td><form:select id='condition' style="width:125px;"
										class='form-control' path='condition' selected='selected' placeholder="Condition" >
										<option value="Excellent">Excellent</option>
										<option value="Very Good">Very Good</option>
										<option value="Good">Good</option>
										<option value="Fair">Fair</option>
										<option value="Poor">Poor</option>
									</form:select> 
										<form:errors path="condition" class="error" />
								</td>
								<td>
									<form:input id='fairMarketValue' type='textarea' style="width:50px;" class='form-control'
										 path="fairMarketValue" ></form:input>
								</td>
								
								<td><form:input id='description' class='form-control' path="description" placeholder="Description"/></td>
								<%-- <td><form:select id='description' style="width:200px;"
										class='form-control' path='description' selected='selected' placeholder="Description" >
										<option value="Excellent">Excellent</option>
										<option value="Very Good">Very Good</option>
										<option value="Good">Good</option>
										<option value="Fair">Fair</option>
										<option value="Poor">Poor</option>
									</form:select> 
										<form:errors path="description" class="error" />
								</td>  --%>
							</tr>
							<!-- <tr>
								<td><input type="submit" id="submitBtn" class="formbutton"
									value="Add Donation Item"/></td>
							</tr> -->
						</tbody>
					

				</table>
				<div style="width:864px;">
				<label style="font-size: 11px;font-weight: bold;"> (all fields required)</label>
				</div>
				<input type="submit" id="submitBtn" class="formbutton"
									value="Add Donation Item" />
									
				</form:form>
									
				<h1>Your Donation List</h1>

				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name</th>
							<th class='col-sm-2'>Expiration Date</th>
							<th class='col-sm-1'>Quantity</th>
							<th class='col-sm-1'>Condition</th>
							<th class='col-sm-2'>Fair Market Value</th>
							<th class='col-sm-2'>Description</th>
							<th class='col-sm-2'>Actions</th>
						</tr>
					</thead>
					<tbody>
								<c:forEach var="item" items="${donatedItems}">
									<tr>
										<td><c:out value="${item.itemName}" escapeXml='true'/></td>
										<td><fmt:formatDate value='${item.dateExpires}'></fmt:formatDate></td>
										<td><c:out value="${item.quantity}" escapeXml='true'/></td>
										<td><c:out value="${item.condition}" escapeXml='true'/></td>
										<td><c:out value="${item.fairMarketValue}" escapeXml='true'/></td>
										<td><c:out value="${item.description}" escapeXml='true'/></td>
										<td><a href="<c:url value="/deleteDonation?donorId=${item.id}" />">Delete</a> | <a href="<c:url value="/editDonoritem?donorId=${item.id}" />">Edit</a></td>
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
		$("#itemCategories").click(function(){
			if ($("#itemCategories :selected").length > 3) {
		        alert('You can select upto 3 options only');
		        $("#itemCategories :selected").removeAttr("selected");
		    }
			
		});
	</script>
</body>
</html>
