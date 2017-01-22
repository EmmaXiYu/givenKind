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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/css/bootstrap-theme.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="icon" href="<c:url value="/img/favicon.ico" />" >
<title>Item Detail</title>
</head>
<body>

	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class='container-fluid'>
		<div class='row'>
			<div class='col-sm-offset-3 col-sm-6'>
				<h1>Donate</h1>
				<form:form role='form' class='form-horizontal' action='donateItem'
					method='post' commandName='donatedItemDTO'>
					<div class='form-group required'>
						<label for='donorEmail' class='control-label col-sm-4'>Contact
							Email</label>
						<div class='col-sm-8'>
							<form:input type='email' id='donorEmail' class='form-control'
								path='donorEmail'></form:input>
							<form:errors path='donorEmail' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group required'>
						<label for='itemName' class='control-label col-sm-4'>Item
							Name</label>
						<div class='col-sm-8'>
							<form:input type='text' id='itemName' class='form-control'
								path='itemName'></form:input>
							<form:errors path='itemName' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group required'>
						<label for='quantity' class='control-label col-sm-4'>Quantity</label>
						<div class='col-sm-4'>
							<form:input type='number' step='any' min='1' id='quantity'
								class='form-control' path='quantity'></form:input>
							<form:errors path='quantity' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group required'>
						<label for='condition' class='control-label col-sm-4'>Condition</label>
						<div class='col-sm-8'>
							<form:select path="condition" id = 'condition' items="${conditionList}"
								multiple="false" />
							<form:errors path='condition' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group required'>
						<label for='fairMarketValue' class='control-label col-sm-4'>Fair
							Market Value</label>
						<div class='col-sm-8'>
							<form:input type='text' id='fairMarketValue' class='form-control'
								path='fairMarketValue'></form:input>
							<form:errors path='fairMarketValue' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group required'>
						<label for='description' class='control-label col-sm-4'>Description</label>
						<div class='col-sm-8'>
							<form:textarea rows='5' id='description' class='form-control'
								path='description'></form:textarea>
							<form:errors path='description' class='error'></form:errors>
						</div>
					</div>
					<div class='form-group'>
						<div class='col-sm-offset-4 col-sm-8'>
							<button class='btn btn-default' type="submit" id="submitBtn">Donate</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>
	<script>
	document.getElementById("submitBtn").disabled=true;
	$(":input").keyup(function() {
		var donorEmail = document.getElementById('donorEmail').value;
		var itemName = document.getElementById('itemName').value;
		var quantity = document.getElementById('quantity').value;
		var condition = document.getElementById('condition').value;
		var fairMarketValue = document.getElementById('fairMarketValue').value;
		var description = document.getElementById('description').value;
		if(!donorEmail || !itemName || !quantity || !condition || !fairMarketValue || !description)
		{
			document.getElementById("submitBtn").disabled=true;
		}
		else{
			document.getElementById("submitBtn").disabled=false;
		}
	});
	
	</script>
</body>
</html>