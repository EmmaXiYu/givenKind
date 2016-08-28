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
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<title>Donor Registration</title>
<script>
function checkEmail(){
	var email = document.getElementById('email').value;
	//alert(email);
}
</script>
</head>
<body>
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id="mainContainer" class='container-fluid'>
	<div class='row'>
		<div class='col-sm-offset-2 col-sm-8'>
		<h1>Donor Register</h1>
		<form:form role='form' class='form-horizontal' action='registerDonor' method='post' commandName='donorRegistrationDTO'>
			<div class='form-group required'>
				<label for='email' class='control-label col-sm-4'>Email (User ID)</label>
				<div class='col-sm-8'>
				
					<form:input type='email' id='email' class='form-control' path='email' onblur='checkEmail()'></form:input>
					<form:errors path='email' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='password' class='control-label col-sm-4'>Password</label>
				<div class='col-sm-8'>
					<form:input type='password' id='password' class='form-control' path='password'></form:input>
					<form:errors path='password' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='confirmPassword' class='control-label col-sm-4'>Confirm Password</label>
				<div class='col-sm-8'>
					<form:input type='password' id='confirmPassword' class='form-control' path='confirmPassword'></form:input>
					<form:errors path='confirmPassword' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='name' class='control-label col-sm-4'>Name</label>
				<div class='col-sm-8'>
					<form:input type='text' id='name' class='form-control' path='name'></form:input>
					<form:errors path='name' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='contactPhone' class='control-label col-sm-4'>Phone</label>
				<div class='col-sm-8'>
					<form:input type='text' id='name' class='form-control' path='contactPhone'></form:input>
					<h4>*Enter number using following format: ZZZYYYYYYY <br /> ZZZ = Area Code, YYYYYYY = Phone Number </h4>
					<form:errors path='contactPhone' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='address1' class='control-label col-sm-4'>Address</label>
				<div class='col-sm-8'>
					<form:input type='text' id='address1' class='form-control' path='address1'></form:input>
					<form:errors path='address1' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group'>
				<label for='address2' class='control-label col-sm-4'></label>
				<div class='col-sm-8'>
					<form:input type='text' id='address2' class='form-control' path='address2'></form:input>
					<form:errors path='address2' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
			    <label for='city' class='control-label col-sm-4'>City/State/Zip</label>
			    <div class='col-sm-4'>
					<form:input type='text' id='city' class='form-control' path='city'></form:input>
					<form:errors path='city' class='error'></form:errors>
				</div>
				<div class='col-sm-2'>
	                 <form:select id='state' class='form-control' path='state' items="${stateList}"></form:select>
                     <form:errors path='state' class='error'></form:errors>
				</div>
				<div class='col-sm-2'>
                     <form:input type='text' id='zip' class='form-control' path='zip'></form:input>
                     <form:errors path='zip' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='reCAPTCHA' class='control-label col-sm-4'>reCAPTCHA</label>
				<div class='col-sm-8'>
					<div class="g-recaptcha" data-sitekey="6Ld1FicTAAAAAJ2ArxAlDYXjC18u4rdrousxUb_V"></div>
				</div>
			</div>

			<div class='form-group'>
				<div class='col-sm-offset-4 col-sm-8'>
					<button class='btn btn-default' type="submit">Register</button>
				</div>
			</div>
		</form:form>
		</div>

	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
