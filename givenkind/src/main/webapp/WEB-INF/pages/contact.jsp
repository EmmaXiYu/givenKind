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
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
<link rel ="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="icon" href="<c:url value="/img/favicon.ico" />" >
<title>Support Us</title>
</head>
<body id="grayBackground">

<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id='mainContainer' class='container-fluid'>
	<div class='row'>
		<div class='col-sm-offset-2 col-sm-8'>
		
		<h2 class="pg-header">SUPPORT US</h2>
		<p class="pg-description">giveNkind is creating a service unlike any other, but like all charitable organizations we rely on the kindness of others to continue providing our 
		services to the nonprofit community.</p>
		
		<h2 class="pg-header">Why give a cash donation to giveNkind?</h2>
		<p class="pg-description">We are a 100% volunteer organization!  We have no paid staff members which means 100% of your donation goes directly to funding our project to help nonprofits 
		find the materials they need to impact their communities.</p>

		<p class="pg-description">Supporting us supports nonprofits nationwide.  Your dollars help us to create and sustain our services, which in turn supports the many other nonprofits that will use 
		giveNkind across the country.</p>
		
		<p class="pg-description">You can actually see the results of your donation.  Our site will collect data that will measure the impact that your support is making by measuring the number of connections 
		between potential donors and nonprofits, the estimated value of the items being donated, and the number of repeat donors that use the site.</p>
		
		<h2 class="pg-header">Donate to giveNkind:</h2>
		<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_blank">
			<input type="hidden" name="cmd" value="_s-xclick">
			<input type="hidden" name="hosted_button_id" value="NLUPF4SQBMSEL">
			<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" style="border:0" name="submit" width="20%" height="20%" alt="PayPal - the safer, easier way to pay online!">
			<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
		</form>
		
		<h2 class="pg-header">Mailing Address:</h2>
		<p class="pg-description">giveNkind<br>
		1650 Lind Lane<br>
		Gurnee, IL 60031</p>
		
		<h2 class="pg-header">Email Us:</h2>
		<a style="font-size:15pt" href="mailto:contact@givenkind.org?subject=Feedback">contact@givenkind.org</a>

		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>
