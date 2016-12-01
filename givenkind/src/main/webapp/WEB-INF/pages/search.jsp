<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<link rel="stylesheet" href="<c:url value="/css/jquery.dataTables.min.css" />">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<title>Search</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
	<div id="mainContainer" class='container-fluid'>
		<div class='row'>
			<div class=' col-sm-9'>
			<c:if test="${booleanNPuser==false}">
				<h1>Search Nonprofit Wish Lists</h1>
			</c:if>
			<c:if test="${booleanNPuser==true}">
				<h1>Search Donation Lists</h1>
			</c:if>
				<table id='searchResults' class='table table-hover'>
				    <thead>
				        <tr>
							<th>Zip Code</th>
							<th>Item Name</th>
							<th>Item Categories</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sr" items="${searchResults}">
							<tr>
								<td><c:out value="${sr.zipCode}" escapeXml='true'/></td>
								<td><a href="${sr.itemDetailsUrl}"><c:out value="${sr.itemName}" escapeXml='true'/></a></td>
								
								<td>
									<c:choose>
										<c:when test="${not empty sr.itemCategories}">
											<c:forEach var="itemCategory" items="${sr.itemCategories}">
												${itemCategory}<br>
											</c:forEach>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table>
					<tr>
						<td class='col-sm-3'>
							<c:choose>
								<c:when test="${hasPreviousPage}">
									<a href="<c:url value="${previousPageQueryParams}"/>" > Previous </a>
								</c:when>
								<c:when test="${not hasPreviousPage}"> Previous </c:when>
							</c:choose>
							|
							<c:choose>
								<c:when test="${hasNextPage}">
									<a href="<c:url value="${nextPageQueryParams}"/>" > Next </a>
								</c:when>
								<c:when test="${not hasNextPage}"> Next </c:when>
							</c:choose>
							</td>
						<td class='col-sm-3'>					
							<c:if test="${booleanFormPost}">
							<a href="search">Back to All Items </a>
							</c:if>
						</td>
						
					</tr>
				</table>	
			</div>
			<div class=' col-sm-3'>
				<h1>Search Criteria</h1>
				<form:form role='form' class='form-horizontal' action='search' method='post' commandName='searchCriteriaDTO'> 
		    		<div class='form-group'>
						<label for='keyword' class='control-label col-sm-4'>Keyword</label>
						<div class='col-sm-8'>
							<form:input type='text' id='keyword' class='form-control' path='keyword'></form:input>
							<form:errors path='keyword' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group'>
						<label for='zipCode' class='control-label col-sm-4'>Zip Code</label>
						<div class='col-sm-8'>
							<form:input type='text' id='zipCode' class='form-control' path='zipCode'></form:input>
							<form:errors path='zipCode' class='error'></form:errors>
						</div>
					
					</div>
					<div class='form-group'>
						<label for='itemCategories' class='control-label col-sm-4'>Item Categories</label>
						<div class='col-sm-8'>
							<form:select id='itemCategories' class='form-control' path='itemCategories' items="${itemCategories}" multi="true"></form:select>
							<form:errors path='itemCategories' class='error'></form:errors>
						</div>
					</div>					
					<c:if test="${booleanNPuser==false}">
					<div class='form-group'>
						<label for='nonprofitCategories' class='control-label col-sm-4'>Non-profit Categories</label>
						<div class='col-sm-8'>
							<form:select id='nonprofitCategories' class='form-control' path='nonprofitCategories' items="${nonprofitCategories}"></form:select>
							<form:errors path='nonprofitCategories' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group'>
						<label for='pickUpService' class='control-label col-sm-4'>Pickup Service Only</label>
						<div class='checkbox'>
							<label for='pickUpService'>
								<form:checkbox id='pickUpService' path='pickUpService'></form:checkbox> 
								<form:errors path='pickUpService' class='error'></form:errors>
							</label>
						</div>
					</div>
					</c:if>
					
					<input type="hidden" name="pageNumber" value="0">
					<div class='form-group'>
						<div class='col-sm-offset-4 col-sm-8'>
							<form:button class='btn btn-default' type="submit">Search</form:button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
