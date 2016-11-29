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
<title>Item Detail</title>
</head>
<body>

<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
	<div id="mainContainer" class='container-fluid'>
		<div class='row'>
			<div class='col-sm-offset-3 col-sm-6'>
			<c:if test="${empty error}">
				<table >
					<tr>
						<td>Donor Email:</td>
						<td>
							<c:out value="${donorDTO.contactEmail}" escapeXml='true'/>
						</td>
					</tr>
					<tr>
						<td>Nonprofit Email:</td>
						<td>
							<c:out value="${npDTO.contactEmail}" escapeXml='true'/>
						</td>
					</tr>
					<tr>
						<td>Nonprofit Item Name:</td>
						<td>
							<c:out value='${wishDTO.itemName}' escapeXml='true'/>
						</td>
					</tr>
					<tr>
					<c:url var="startTrans" value="finishAddingDonorTransaction?itemId=${wishDTO.id}"/>
						<form:form role="form" modelAttribute='transDTO' name="finishAddingDonorTransaction" action="${startTrans}" method='POST' id="finishAddingDonorTransaction">
							<td>Enter number of items to donate:</td>
								<td><form:input type='number' class='form-control'
									step='1' min='1' max ="${wishDTO.quantityDesired}"  path="quantity" id="quantity" value="${transDTO.quantity}"></form:input>									
									
								</td>
					</tr>
					<tr>
						<td><input type="submit" id="submitBtn" class="formbutton"
						value="Finish Editing Transaction Details"/></td>
					
					
				</form:form>
					<td>
							<a href='<c:url value="/search"/>'><button>Cancel</button></a>
						</td>
						</tr>
				</table>
				</a>
				
			</c:if>
			</div>
			
		</div>
	</div>
	<%@ include file="footer.jsp" %>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/jquery-ui-1.11.2/jquery-ui.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
	<script>
		//document.getElementById("submitBtn").disabled=true;
		$(function() {
			$(".datepicker").datepicker();

			$("#addItemForm").on("submit", function(event) {
				event.preventDefault();
			});
		}); 
		
		 
	</script>
</body>