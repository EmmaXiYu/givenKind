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
<title>Your Active Transactions</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Your Active Transactions</h1>
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name (Requests to Confirm)</th>
							<th class='col-sm-2'>Item Name (Your Requests)</th>
							<th class='col-sm-2'>Quantity </th>
							<th class='col-sm-2'>Status </th>
							<th class='col-sm-2'>Donor Email</th>
							<th class='col-sm-2'>Nonprofit Email</th>
							<th class='col-sm-2'>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="trans" items="${transList}">
							<tr>
								<td><c:out value="${trans.donorItem.itemName}" escapeXml='true'/></td>
								<td><c:out value="${trans.npItem.itemName}" escapeXml='true'/></td>
								<td><c:out value="${trans.quantity}" escapeXml='true'/></td>
								<td><c:out value="${trans.statusCategory.statusCategoryName}" escapeXml='true'/></td>
								<td><c:out value="${trans.donorProfile.user.loginId}" escapeXml='true'/></td>
								<td><c:out value="${trans.npProfile.user.loginId}" escapeXml='true'/></td>
								<td>
									<c:choose>
										<c:when test="${trans.statusCategory.statusCategoryName eq 'NP Requested'}">
										<a href="<c:url value="/confirmDonorTransaction?transactionId=${trans.id}" />">Accept</a> | <a href="<c:url value="/cancelDonorTransaction?transactionId=${trans.id}" />">Cancel</a>
										</c:when>
										<c:when test="${trans.statusCategory.statusCategoryName eq 'Accepted'}">
										<a href="<c:url value="/confirmDonorShipment?transactionId=${trans.id}" />">Confirm Shipment</a> | <a href="<c:url value="/cancelDonorTransaction?transactionId=${trans.id}" />">Cancel</a></td>
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
</html>
