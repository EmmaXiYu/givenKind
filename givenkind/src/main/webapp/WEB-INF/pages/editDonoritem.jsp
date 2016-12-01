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


<title>Edit Donor list Item</title>
</head>
<body>
		<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Edit Donor List Item</h1>
				<a href='<c:url value="/donorlist"/>' role='button' class='button'>Return to Donorlist</a>
				<c:url var="addUrl" value="/completeEditDonor?donorId=${donorlistDTO.id}"/>
				<form:form role="form" modelAttribute='donorlistDTO' name="editDonoritem" action="${addUrl}" method='POST' id="editDonoritem">
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name </th>
							<th class='col-sm-2'>Expiration Date </th>
							<th class='col-sm-1'>Quantity </th>
							<th class='col-sm-3'>Category 
							<span>&nbsp;</span>
							<span style="font-size: 11px;font-weight: bold;">(Limit to 3 categories)</span>
							</th>
							<th class='col-sm-1'>Condition </th>
							<th class='col-sm-1'>Fair Market Value (USD) Per Item</th>
							<th class='col-sm-3'>Description</th>
						</tr>
					</thead>
					
						
						<tbody>
							<tr>
								<td><form:input id='itemName' class='form-control' path="itemName" value="${donorlistDTO.itemName}" maxlength='100'/></td>
								<fmt:parseDate pattern="yyyy-MM-dd" value="${donorlistDTO.dateExpires}" var="parsedStatusDate" />
								<fmt:formatDate pattern="MM/dd/yyyy" value="${parsedStatusDate}" var="formattedStatusDate" />
								<td><form:input class='form-control datepicker' path="dateExpires" id="dateExpires" value="${formattedStatusDate}" /></td>
								<td><form:input id='quantityAvailable'  class='form-control'
										step='1' min='1' path="quantity" value="${donorlistDTO.quantity}" maxlength='10'></form:input></td>
								<td><form:select class='form-control'
										items="${ItemCategoryList}" id='itemCategories'
										path='itemCategories' selected='selected' value="${donorlistDTO.itemCategories}"></form:select>
										<span id="selectError" class="error hidden">Only three categories can be selected</span>
								</td>
								
								<td><form:select id='condition' style="width:125px;"
										class='form-control' path='condition'  placeholder="Condition" >
										<option value="Excellent">Excellent</option>
										<option value="Very Good">Very Good</option>
										<option value="Good">Good</option>
										<option value="Fair">Fair</option>
										<option value="Poor">Poor</option>
									</form:select> 
										<form:errors path="condition" class="error" />
								</td>
								<td><form:input id='fairMarketValue' type='textarea' style="width:50px;"
										 path="fairMarketValue" value = "${donorlistDTO.fairMarketValue}" maxlength='10'></form:input></td>
								<td><form:input id='description' type='textarea'
										class='form-control' path='description' value="${donorlistDTO.description}" maxlength='100'></form:input>
								</td>
							</tr>
							</tbody>
							</table>
							<div style="width:864px;">
							<label style="font-size: 11px;font-weight: bold;"> (all fields required)</label>
							</div>
							
								<td><input id="submitBtn" type="submit" class="formbutton" 
									value="Update Donor Item"/></td>
							
						
						
					</form:form>
				
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
			var quantityAvailable = document.getElementById('quantityAvailable').value;
			var itemCategories = document.getElementById('itemCategories').value;
			var fairMarketValue = document.getElementById('fairMarketValue').value;
			var description = document.getElementById('description').value;
			var condition = document.getElementById('condition').value;
			if(!itemName || !dateExpires || !quantityAvailable || !itemCategories || !fairMarketValue || !description||!condition)
			{
				document.getElementById("submitBtn").disabled=true;
			}
			else{
				document.getElementById("submitBtn").disabled=false;
			}
		});
		$(document).ready(function(){
	           $("#condition").val("${donorlistDTO.condition}").attr('selected', 'selected');
	     }); 
	</script>

</body>
</html>
