<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="icon" href="<c:url value="/img/favicon.ico" />" >
<title>Item Detail</title>
</head>
<body>

<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
	<div id="mainContainer" class='container-fluid'>
		<div class='row'>
			<div class='col-sm-offset-3 col-sm-6'>
			<c:if test="${empty error}">
				<c:if test="${not empty wishlistDTO }">
				<table  class='table'>
					<tr>
						<td>Item Name:</td>
						<td><c:out value="${wishlistDTO.itemName}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Item Categories:</td>
						<td>
						<c:forEach var="itemCategory" items="${wishlistDTO.wishlistItemCategories}">
							${itemCategory}<br>
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td>Quantity Desired:</td>
						<td><c:out value="${wishlistDTO.quantityDesired}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Date Expires:</td>
						<td><fmt:formatDate value='${wishlistDTO.dateExpires}'></fmt:formatDate></td>
					</tr>
					<tr>
						<td>Description:</td>
						<td><c:out value="${wishlistDTO.note}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Impact:</td>
						<td><c:out value="${wishlistDTO.impact}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Comments:</td>
						<td><c:out value="${wishlistDTO.comments}" escapeXml='true'/></td>
					</tr>
				
				<tr>
				<td>
					<a href="startDonorTransaction?itemId=${wishlistDTO.id}">
		   					<button>Start a Transaction</button></a>
	   			</td>				  
				 <td>
							<a href='<c:url value="/search"/>'><button>Cancel</button></a>
				</td>
					</tr>
					</table>
				</c:if>
				
				<c:if test="${not empty donorlistDTO }">
				<table class='table'>
					<tr>
						<td>Item Name:</td>
						<td><c:out value="${donorlistDTO.itemName}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Item Categories:</td>
						<td>
						<c:forEach var="itemCategory" items="${donorlistDTO.itemCategories}">
							${itemCategory}<br>
						</c:forEach>
						</td>
					</tr>
					<tr>
						<td>Quantity Desired:</td>
						<td><c:out value="${donorlistDTO.quantity}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Date Expires:</td>
						<td><fmt:formatDate value='${donorlistDTO.dateExpires}'></fmt:formatDate></td>
					</tr>
					
					<tr>
						<td>Fair market value:</td>
						<td><c:out value="${donorlistDTO.fairMarketValue}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Condition:</td>
						<td><c:out value="${donorlistDTO.condition}" escapeXml='true'/></td>
					</tr>
					<tr>
						<td>Description:</td>
						<td><c:out value="${donorlistDTO.description}" escapeXml='true'/></td>
					</tr>
			
				<tr>
				<td>
				<a href="startNpTransaction?itemId=${donorlistDTO.id}">
   						<button>Start a Transaction</button>
						</a>
					
				   </td>				  
				 <td>
							<a href='<c:url value="/search"/>'><button>Cancel</button></a>
				</td>
					</tr>
						</table>
				</c:if>

			</c:if>
			</div>
			
		</div>
	</div>
	<%@ include file="footer.jsp" %>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/validation.js" />"></script>
</body>