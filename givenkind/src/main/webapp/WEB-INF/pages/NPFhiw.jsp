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
<title>Nonprofit Information</title>
<style>
.btn-lg { font-size: 36px }
</style>
</head>
<body>

<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id='mainContainer' class='container-fluid'>

    <div id='content' class='row'>
		<div class='col-sm-offset-2 col-sm-8'> 
			<h1>How It Works - As a Nonprofit Organization</h1>
			<h2>Looking for items to support what you do, but don't have the budget to get them?</h2>
		 
			<p>giveNkind is here to help! Post your wish list to our free, nationwide platform to directly connect with donors
			that can help you continue to do good within your community, or browse our database of items that donors are already
			looking to give to see if they have anything that you need.</p>
			 
			<h2>Here's how it works:</h2>
			
			<h3>1. Register with us.</h3>
			
			<em>To use our services, your charitable organization must be a <a href="http://www.irs.gov/Charities-&-Non-Profits/Charitable-Organizations/Exemption-Requirements-Section-501%28c%29%283%29-Organizations">501(c)(3) registered nonprofit.</a></em>
			<br/>
			<br/>
			<p>Register your organization <a href='register'>here</a> using your <a href="http://www.irs.gov/Charities-&amp;-Non-Profits/Employer-Identification-Number">Employer Identification Number (EIN)</a> and we'll verify your
			registration and send you a confirmation email within 1 business day.</p>
			
			<h3>2. Post your wish list.</h3>
			<p>Click the link in your confirmation email and follow the instructions provided to post your <a href="/wishlist">wishlist</a> on givenkind.org.  By posting your wish list, your organization becomes part of our searchable database of nonprofits in need, and will be visible to all donors who visit our site.</p>  
			 
			<h3>3. Connect with a donor.</h3>
			<p>At giveNkind, we believe in creating direct connections between donors with items to give and the nonprofits that need them.  Once a potential match is identified, contact information is provided so the two parties can communicate directly with one another without a middle man.  There are two ways this connection can happen:</p>
			<ol>
				<li> Sit back, relax and wait for a match.  Donors can search our nonprofit database by geographic location, charitable cause or specific wish list item.  If your organization matches a donor's search criteria, your profile and contact information will appear in the list of search results and the donor will be able to email you about donating goods to your organization.</li>
				<li> Actively search for items available for donation.  If patience isn't your thing, you don't have to wait around for donors to contact you.  You can also type the name of an item from your wish list into the search bar to view a list of available items that donors have posted to our donation database.  If a donor's post matches your search criteria, their contact information will appear in the list of search results and you can email them about the item.</li>
			</ol> 
						
			<h3>4. Receive a donation.</h3>
			<p>Once initial email contact is established, you will need to determine how, when and where you will meet the donor to exchange the donated goods for a <a href="/faq">tax receipt</a>.  After the exchange, you are free to use the new item(s) you receive to impact the community you serve and the donor gets to claim their charitable donation on this year's taxes.  <em>Win-win</em>.</p>
			 	 
			<h3>5. Update your wish list.</h3>
			<p>Our services are intended for more than just one-time use.  After receiving an item from a donor, make sure you update your <a href="/faq">wishlist</a> to reflect receipt of the donation so other donors using the site know that you no longer need to item.  You can also post more items that you need to keep donors up-to-date on how they can help you!</p>
			 
		</div>
	</div>
</div>
			
<%@ include file="footer.jsp" %>

</body>
</html>
