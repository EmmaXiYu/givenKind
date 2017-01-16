<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Admin</title>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container Expandable" align=left style="width: 750; height: 240; overflow-x: hidden; overflow: auto; position:relative; xtop: 253px; xleft:20px; padding: 0px; z-index: 1">
		<div class='row'>
			<div class='col-sm-offset-1 col-sm-10'>
			    <h1>Nonprofit Approval Status</h1>
				<table class='table table-hover'>
				    <thead>
					    <tr>
							<th>Id</th>
							<th>Name</th>
							<th>Email Address</th>
							<th>Approved</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
					<c:when test="${not empty profileList}">
					<c:forEach var="profileList" items="${profileList}">
						<tr>
							<td><c:out value="${profileList.id}" escapeXml='true'/></td>
							<td><c:out value="${profileList.fullName}" escapeXml='true'/></td>
							<td><c:out value="${profileList.contactEmail}" escapeXml='true'/></td>
							<td><c:out value="${profileList.approvalStatus}" escapeXml='true'/></td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise><td><c:out value="${profileList.approvalStatus}" escapeXml='true'/></td>
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
	<%@ include file="footer.jsp" %>
	<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
	<script src="<c:url value="/js/jquery-ui-1.11.2/jquery-ui.min.js" />"></script>


	<script src="<c:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>

