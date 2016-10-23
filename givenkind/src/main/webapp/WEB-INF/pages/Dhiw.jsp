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
<title>Donor Information</title>
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
			<h1>How It Works - As a Donor</h1>
			
			<h2>Do you want to make a purposeful donation directly to nonprofits who really need the items you have to give?</h2>

			<p>giveNkind can help!  Post the items you have to donate to our free, nationwide platform so nonprofits in need can find you, or use our custom search feature to find organizations registered on giveNkind.org that fit your unique donation criteria.  
			Since all of the nonprofits in our database are 501(c)(3) organizations, your donation is tax deductible and goes to helping nonprofits make the world a better place.</p>
 
			<h2>Here’s how it works:</h2>

			<h3>1. Register with us.</h3>

			<p>To use our services, create a donor profile <a href="registerDonor">here</a></p>
 

			<h3>2. Post items available for donation.</h3>

			<p>Click the link in your confirmation email and follow the instructions provided to post the item or items you wish 
			to donate on givenkind.org.  By posting your items, they become part of our searchable database of items 
			available for donation, and will be visible to all nonprofits who register to use our services.</p>  

			<h3>3. Connect with a nonprofit.</h3>

			<p>At giveNkind, we believe in creating direct connections between donors with items to give and the nonprofits 
			that need them.  Once a potential match is identified, contact information is provided so the two parties can 
			communicate directly with one another without a middle man.  After your item or items are posted, there are 
			two ways this connection can happen:</p>
			<ol type="a">
			<li> Sit back, relax and wait for a match.  Nonprofits can search our database for specific items on their wishlist.</li>
			
			<li> Actively search for nonprofits in need.  If patience isn't your thing, you don't have to wait around for 
			wish list that are available for donation.  If your item matches a nonprofit's search criteria, your profile 
			and contact information will appear in the list of search results and they will be able to contact you 
			about receiving your item.</li>
			
			<li> Wait for nonprofits to contact you.  You can also use our database to search for nonprofits (1) by geographic 
			location, charitable cause or specific item needed.  If a nonprofit's profile or wish list matches your 
			search criteria, their contact information will appear in the list of search results and you can contact 
			them about making a donation to their cause.</li>
			</ol>

			<h3>4. Receive a tax receipt for your donation.</h3>

			<p>Once initial email contact is established, you will need to determine how, when and where you will meet the 
			nonprofit to exchange the goods you wish to donate for a tax receipt (1).  After the exchange, you'll have a tax 
			receipt you can use to claim your charitable donation on this year's taxes, and the nonprofit will be able to use 
			the new item(s) you donated to impact the community they serve.  <em>Win-win</em>.</p>

			<h3>5. Update your Donation List.</h3>
			
			<p>Our services are intended for more than just one-time use.  After donating an item to a nonprofit, make sure 
			you remove the item from your profile so other nonprofits using the site know that your item is no longer 
			available. You can also post more items that you have to give to keep nonprofits up-to-date on you can help 
			them continue to do good in their communities.</p>
			
		</div>
	</div>
</div>
			
<%@ include file="footer.jsp" %>
<script src="<c:url value="/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>
