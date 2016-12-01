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
<title>Your Completed Transactions</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Your Completed Transactions</h1>
				
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name (Your Accepted Donations)</th>
							<th class='col-sm-2'>Item Name (Your Donations)</th>
							<th class='col-sm-2'>Item Description</th>
							<th class='col-sm-2'>Quantity </th>							
							<th class='col-sm-2'>NonProfit Email</th>
							
							
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="trans" items="${transList}" >
						
							<tr>
								<td><c:out value="${trans.npItemId.itemName}" escapeXml='true'/></td>
								<td><c:out value="${trans.donorItemId.itemName}" escapeXml='true'/></td>
								<c:choose>
										<c:when test="${not empty trans.donorItemId.description }">
											<td><c:out value="${trans.donorItemId.description}" escapeXml='true'/></td>
										</c:when>
										<c:when test="${not empty trans.npItemId.notes }">
											<td><c:out value="${trans.npItemId.notes}" escapeXml='true'/></td>
										</c:when>
										<c:otherwise>

										</c:otherwise>
								</c:choose>
								
								<td><c:out value="${trans.quantity}" escapeXml='true'/></td>
								
								
								
								<td><c:out value="${trans.npProfileId.contactEmail}" escapeXml='true'/></td>
								
								
							</tr>
							</c:forEach>
						
					
					</tbody>
				</table>
			</div>
		</div>			
	</div>
	<%@ include file="footer.jsp"%>
</body>




</html>