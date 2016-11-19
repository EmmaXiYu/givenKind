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
<title>Your Active Transactions</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Your Active Transactions</h1>
				<c:if test="${not empty msg}">
            		<div class="error col-sm-12">${msg}</div>
       			 </c:if>
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name (Requests to Confirm)</th>
							<th class='col-sm-2'>Item Name (Your Requests)</th>
							<th class='col-sm-2'>Quantity </th>
							<th class='col-sm-2'>Status </th>							
							<th class='col-sm-2'>Nonprofit Email</th>
							<th class='col-sm-2'>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach begin="0" var="trans" items="${transList}" end="${transList.size()}" varStatus="loop">
							<tr>
								<td><c:out value="${trans.donorItem.itemName}" escapeXml='true'/></td>
								<td><c:out value="${trans.npItem.itemName}" escapeXml='true'/></td>
								<td><label for="name" class="control-label" id="text-info${loop.index}"><c:out value="${trans.quantity}" escapeXml='true'/></label></td>
								<td><c:out value="${trans.statusCategory.statusCategoryName}" escapeXml='true'/></td>								
								<td><c:out value="${trans.npProfile.user.loginId}" escapeXml='true'/></td>
								<td>
									<c:choose>
										<c:when test="${trans.statusCategory.statusCategoryName eq 'NP Requested'}">
										<a href="<c:url value="/confirmDonorTransaction?transactionId=${trans.id}" />" id="accept${loop.index}"  onclick="hlink(this,${loop.index})">Accept</a> | <a href="<c:url value="/cancelDonorTransaction?transactionId=${trans.id}" />">Cancel</a> | <a href="#" id="edit" onclick="enableQty(${loop.index})">Edit Quantity</a>
										</c:when>
										<c:when test="${trans.statusCategory.statusCategoryName eq 'Accepted'}">
										<a href="<c:url value="/confirmDonorShipment?transactionId=${trans.id}" />">Confirm Shipment</a> | <a href="<c:url value="/cancelAcceptedDonorTransaction?transactionId=${trans.id}" />">Cancel</a>
										</c:when>
										<c:when test="${trans.statusCategory.statusCategoryName eq 'Donor Requested'}">
										<a href="<c:url value="/cancelDonorTransaction?transactionId=${trans.id}" />">Cancel</a>
										</c:when>
										
										<c:otherwise>

										</c:otherwise>
									</c:choose>
								</td>
							</tr>
					    </c:forEach>
					</tbody>
				</table>
			</div>
		</div>			
	</div>
	<%@ include file="footer.jsp"%>
</body>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/validation.js" />"></script>
<script src="<c:url value="/js/jquery.mask.min.js" />"></script>
<script>
function enableQty(x){
	
	 var text = $('#text-info'+x).text();		 
	 var input = $('<input type="text" id="editQty" value="' + text + '" maxlength="5" onkeypress="return isNumeric(event)" class="form-control" />');		
	 $('#text-info'+x).text('').append(input);
	};

function hlink(obj,x){
	 
	  var  value;	 

	  if(!document.getElementById('editQty')){
			
		 value = $('#text-info'+x).text();		 
		  
	  }else{		 
			 value=document.getElementById('editQty').value;  
		  
	  }
	  var url = obj.href;
	  url = url + '&qty=' + value;
   
  	 $('#accept'+x).attr("href",url);  
   
   return false;
};
	  
function isNumeric(evt)
		{
			var charCode = (evt.which) ? evt.which : event.keyCode;
			if( !( charCode > 47 && charCode < 58)) 
				return false;		
			return true;
		};
	

</script>

</html>
