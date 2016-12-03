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
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<!-- <script>
function checkEmail(){
	var email = document.getElementById('email').value;
	$.ajax({
		type : 'GET',
        url : 'registerCheck?email='+email,
        success : function(data) {
            echo data;
        }
    });
}
</script> -->
<title>Registration</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id="mainContainer" class='container-fluid'>
	<div class='row'>
		<div class='col-sm-offset-2 col-sm-8'>
		<h1>Non-Profit Register</h1>
		<form:form role='form' class='form-horizontal' action='register' method='post' commandName='registrationDTO'>
		    
			<div class='form-group required'>
				<label for='email' class='control-label col-sm-4'>Email (User ID)</label>
				<div class='col-sm-8'>
					<form:input type='email' pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" id='email' class='form-control' path='email' maxlength='100' required="true"></form:input>
					<h4>*Enter valid format: For example, someone@domain.com or someone@domain.org </h4>
					<form:errors path='email' class='error'></form:errors>
					<span id="emailError" class="error hidden">Enter Valid Email </span>
				</div>
			</div>
			<div class='form-group required' >
				<label for='password' class='control-label col-sm-4'>Password</label>
				<div class='col-sm-8'>
					<form:input type='password' id='password' class='form-control' path='password' maxlength='20' required="true"></form:input>
					<form:errors path='password' class='error'></form:errors>
					<span id="passwordError" class="error hidden">Password must be minimum 8 characters</span>
				</div>
			</div>
			<div class='form-group required'>
				<label for='confirmPassword' class='control-label col-sm-4'>Confirm Password</label>
				<div class='col-sm-8'>
					<form:input type='password' id='confirmPassword' class='form-control' path='confirmPassword'  maxlength='20' required="true"></form:input>
					<form:errors path='confirmPassword' class='error'></form:errors>
					<span id="confirmPasswordError" class="error hidden">Passwords do not match</span>
				</div>
			</div>
			<div class='form-group required'>
				<label for='organizationName' class='control-label col-sm-4'>Organization Name</label>
				<div class='col-sm-8'>
					<form:input type='text' id='organizationName' class='form-control' path='organizationName' maxlength='200' required="true"></form:input>
					<form:errors path='organizationName' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='missionStatement' class='control-label col-sm-4'>Mission Statement</label>
				<div class='col-sm-8'>
					<form:textarea id='missionStatement' class='form-control' path='missionStatement' maxlength='100' required="true"></form:textarea>
					<form:errors path='missionStatement' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='website' class='control-label col-sm-4'>Website</label>
				<div class='col-sm-8'>
					<form:input type='text' id='website' class='form-control' path='website' maxlength='200' ></form:input>
					<form:errors path='website' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='address1' class='control-label col-sm-4'>Address</label>
				<div class='col-sm-8'>
					<form:input type='text' id='address1' class='form-control' path='address1' maxlength='100' required="true"></form:input>
					<form:errors path='address1' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group'>
				<div class='col-sm-offset-4 col-sm-8'>
					<form:input type='text' id='address2' class='form-control' maxlength='100' path='address2'></form:input>
					<form:errors path='address2' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group'>
				<div class='col-sm-offset-4 col-sm-4 required'>
					<label for='city' class='control-label'>City</label>
					<form:input type='text' id='city' class='form-control' path='city' maxlength='100' required="true"></form:input>
					<form:errors path='city' class='error'></form:errors>
				</div>
				<div class='col-sm-2 required'>
					<label for='state' class='control-label'>State</label>
					<form:select id='state' class='form-control' path='state' items="${stateList}" required="true"></form:select>
					<form:errors path='state' class='error'></form:errors>
				</div>
				<div class='col-sm-2 required'>
					<label for='zip' class='control-label'>Zip</label>
					<form:input type='text' id='zip' class='form-control zipMask' path='zip' maxlength='10' onkeypress="return isNumeric(event)" required="true"></form:input>
					<form:errors path='zip' class='error'></form:errors>
					<span id="zipError" class="error hidden">ZIP codes must contain 5 or 9 digits</span>
				</div>
			</div>
			<div class='form-group'>
				<label class='control-label col-sm-4'>Pickup Service</label>
				
					<div class='col-sm-4'>
					<div class='checkbox'>
						<label for='pickupService'>							
							<form:radiobutton value='Yes' id='pickupServiceY' path='pickupService' checked="checked"/>Yes
							<form:radiobutton value='No' id='pickupServiceN' path='pickupService'/>No 
							<form:errors path='pickupService' class='error'></form:errors>
						</label>
					</div>
				</div>
				
				<div class='col-sm-4'>
					<label for='pickupDistance' class='control-label'>Distance Willing To Travel(in Miles)</label>
					<form:input type='text'  min='1' id='pickupDistance' class='form-control'  path='pickupDistance' maxlength='9' onkeypress="return isNumeric(event)"></form:input>
					<form:errors path='pickupDistance' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='employerIdentificationNumber' class='control-label col-sm-4'>EIN</label>
				<div class='col-sm-8'>
					<form:input type='text' id='employerIdentificationNumber' class='form-control' path='employerIdentificationNumber' maxlength='9' required="true" onkeypress="return isNumeric(event)"></form:input>
					<form:errors path='employerIdentificationNumber' class='error'></form:errors>
					<span id="einError" class="error hidden">EIN must be 9 digits long</span>
				</div>
			</div>
			<div class='form-group required'>
				<label for='nonprofitCategories' class='control-label col-sm-4'>Nonprofit Categories<br />(use Ctrl+Click to select up to three)</label>
				<div class='col-sm-8'>
					<form:select multiple='true' class='form-control' items="${nonprofitCategoryList}"   id='nonprofitCategories' path='nonprofitCategories' required="true"></form:select>
					<form:errors path='nonprofitCategories' class='error'></form:errors>
					<span id="selectError" class="error hidden">Only three categories can be selected</span>
				</div>
			</div>
			<div class='form-group required'>
				<label for='contactPerson' class='control-label col-sm-4'>Contact Person</label>
				<div class='col-sm-8'>
					<form:input type='text' id='contactPerson' class='form-control' path='contactPerson' maxlength='100' required="true"></form:input>
					<form:errors path='contactPerson' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'>
				<label for='contactEmail' class='control-label col-sm-4'>Contact Email</label>
				<div class='col-sm-8'>
					<form:input type='email' pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"  id='contactEmail' class='form-control' path='contactEmail' maxlength='100' required="true"></form:input>
					<h4>*Enter valid format: For example, someone@domain.com or someone@domain.org </h4>
					<form:errors path='contactEmail' class='error'></form:errors>
				</div>
			</div>
			<div class='form-group required'> 
				<label for='contactPhone' class='control-label col-sm-4'>Contact Phone</label>
				<div class='col-sm-8'>
					<form:input type="tel" pattern="^[0-9]{10}$" id='contactPhone' class='form-control' path='contactPhone' maxlength='20' required="true" onkeypress="return isNumeric(event)"></form:input>
					<h4>*Enter number using following format: ZZZYYYYYYY <br /> ZZZ = Area Code, YYYYYYY = Phone Number </h4>
					<form:errors path='contactPhone' class='error'></form:errors>
					<span id="phoneError" class="error hidden">Phone number must be minimum 10 digits</span>
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
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/register.js" />"></script>
<script src="<c:url value="/js/jquery.mask.min.js" />"></script>
</body>
<script>
function isNumeric(evt)
	{
		var charCode = (evt.which) ? evt.which : event.keyCode;
		if( !( charCode > 47 && charCode < 58)) 
			return false;		
		return true;
	}
	
</script>



