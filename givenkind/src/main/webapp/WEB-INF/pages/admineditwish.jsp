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
<title>Edit Wish List Item</title>
</head>
<body>
		<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Edit Wish List Item</h1>
				<a href='<c:url value="/admin/view/wishList"/>' role='button' class='button'>Return to Wishlist</a>
				<c:url var="editUrl" value="/adminEditWish?wishId=${wishlistDTO.id}"/>
				<form:form role="form" modelAttribute='wishlistDTO' name="editWish" action="${editUrl}" method='POST' id="editWish">
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name </th>
							<th class='col-sm-2'>Expiration Date </th>
							<th class='col-sm-1'>Quantity </th>
							<th class='col-sm-3'>Category </th>
							<th class='col-sm-3'>Description </th>
							<th class='col-sm-2'>Impact </th>
						</tr>
					</thead>
					
					
						<tbody>
							<tr>
								<td><form:input class='form-control' path="itemName" id="itemName" value="${wishlistDTO.itemName}" maxlength="100"/></td>
								
								<td><form:input class='form-control datepicker'
										path="dateExpires" id="dateExpires"></form:input></td>
								<td><form:input  class='form-control'
										step='any' min='1' path="quantityDesired" id="quantityDesired" value="${wishlistDTO.quantityDesired}" maxlength="10"></form:input></td>
								<td><form:select class='form-control'
										items="${ItemCategoryList}" id='itemCategories'
										path='wishlistItemCategories' multiple="true" selected='selected' value="${wishlistDTO.wishlistItemCategories}"></form:select>
								<span id="selectError" class="error hidden">Only three categories can be selected</span></td>
								
								<td><form:input id='note' type='textarea'
										class='form-control' path='note' value="${wishlistDTO.note}" maxlength="100"></form:input>
								</td>
								
								<td>
								 <form:select id='impact' style="width:250px;"
										class='form-control' path='impact'  placeholder="Impact"  >
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
										 path='comments' placeholder="Comments" value="${wishlistDTO}"></form:textarea>
				<form:errors path="comments" class="error" /> 
				<div style="width:864px;">
					<label style="font-size: 11px;font-weight: bold;"> (all fields required)</label>
				</div>
				
					<input type="submit" id="submitBtn" class="formbutton"
									value="Edit Wish"/>			
						
						
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
		//document.getElementById("submitBtn").disabled=true;
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
			if(!itemName || !dateExpires || !quantityDesired || !itemCategories || !note || !impact|| !comments)
			{
				document.getElementById("submitBtn").disabled=true;
			}
			else{
				document.getElementById("submitBtn").disabled=false;
			}
		});
		 $(document).ready(function(){
	           $("#impact").val("${wishlistDTO.impact}").attr('selected', 'selected');
	     });
	</script>

</body>
</html>