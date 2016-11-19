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
<title>Home</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id='mainContainer' class='container-fluid'>
	<div class='row'>
		<div class='col-sm-offset-2 col-sm-8'>
		
		<h1>FAQ</h1>
		
		<h2><em>GENERAL FAQ</em></h2>
		
		<h3>What is giveNkind?</h3>
		<p>Learn more about our purpose, history, and board on our <a href="about">About page</a>.</p>
		
		<h3>Is there a fee to use giveNkind?</h3>
		<p>No, giveNkind is free!  We always welcome donations to keep our site running and to keep making improvements. 
		Visit our <a href="contact">Support page</a> if you would like to help!</p>
				
		<h3>How do tax receipts work?</h3>
		<p>Nonprofits are expected to give a tax receipt to every donor at the time of donation.</p>
		
		<h3>Who determines the value of a gift?</h3>
		<p>The donor determines the value of a donation.  The IRS recommends giving donated items the “fair market value.” 
		For more guidance, please visit this IRS information.</p>
		
		<h3>What is Fair Market Value?</h3>
		<p>Fair Market Value (FMV) is what you can claim on your tax return for the donation of your item.  The FMV is what a 
		buyer would currently pay for your item if you were to resell it.  For example, if you paid $1,000 for a brand new 
		computer several years ago, your deduction would not be $1000 for the donated item, but its estimated current value.  
		For more information on FMV, please visit the IRS page <a href="http://www.irs.gov/pub/irs-pdf/p561.pdf" target="_blank">http://www.irs.gov/pub/irs-pdf/p561.pdf</a></p>
		
		<h3>Why haven’t I heard from a nonprofit or donor I contacted? </h3>
		<p>Please give your nonprofit or donor some time to respond.  If you don’t hear from them in a timely manner, please 
		let us know and we can help!</p>
		
		<h3>How does the transfer of goods take place?</h3>
		<p>Once you and a nonprofit/ donor connect, decide on a time and place for the donation to occur.  Like all in-person 
		transactions, please make sure you meet at a safe, secure location.</p>
		
		<h3>Can an item be mailed?</h3>
		<p>At the moment, the donor is responsible for postage unless an arrangement is made with the nonprofit.  Stay tuned as 
		we are working on partnerships that can make postage hassle- and cost-free!</p>
		
		<h3>Some of my donor-listed items are no longer available or nonprofit wish lists have been filled. How do I take them off the site?</h3>
		<p>Please log in and under your profile you will be able to delete these items from your list.</p>
		
		<h3>What conduct, if any, would result in a user being barred from using the site?</h3>
		<p>Not sending an item that was committed, sending damaged or poor quality items, items that don’t match the description, 
		repeatedly not sending a receipt (nonprofits), repeated long delays in corresponding, and unprofessional conduct.  These 
		repeated actions may be grounds for being removed from the site.</p>
		
		<h3>What happens if I forget my password?</h3>
		<p>Please contact us at info@givenkind.org</p>
		
		<h3>How do I give feedback about my experience?</h3>
		<p>We always are looking to improve your experience.  Let us know what you like and what we can do better by emailing us 
		at info@givenkind.org</p>
		
		<h3>Who do I contact if I have more questions?</h3>
		<p>We would be happy to help you!  Please email us at info@givenkind.org.</p>
		
		<h2><em>FOR DONORS</em></h2>
   		
   		<h3>Will my item be listed as available to donate indefinitely?</h3>
   		<p>You can set the expiration date on your donation offer.  Your offer is set to expire automatically expire after 30 days</p>
   		
		<h2><em>FOR NONPROFITS</em></h2>
		
		<h3>How do I register to receive goods through giveNkind?</h3>
		<p>Nonprofits can join giveNkind through our online registration tool. Membership is free, but in order to qualify 
		you must be a 501(c)(3) tax-exempt recognized public charity.</p>
		
		<h3>My application for 501(c)(3) is being processed.  Can I still receive items from the giveNkind site?</h3>
		<p>At this time we are only serving registered 501(c)(3)  organizations, but we would love to serve you as soon as you 
		receive your IRS letter of determination.</p>
		
		<h3>Can I request items on giveNkind if I am not a nonprofit organization?</h3>
		<p>Only organizations with 501(c)(3) status are approved to receive donations.  If you are looking to become a 501(c)(3) 
		organization, please visit the application found here.</p>
		
		<h3>What kind of items can I request?</h3>
		<p>All types of items!  To truly understand what we mean, take the site for a test drive. Use the search function to see 
		what kinds of items are needed in your area.  You may find that what nonprofits need is something you already have!</p>
		
		<h3>Can nonprofits request a service through this site?</h3>
		<p>We currently only allow postings for material donation needs.</p>
		
		<h3>As a nonprofit, how do I know what I am getting and its condition?</h3>
		<p>You can list specific requests for any items you are seeking in your wish list.  Similarly, donors can post details 
		about the items they are looking to give.  To be sure, we recommend you connect with the potential donor and ask questions.</p>
		
		<h3>As a nonprofit, if there is a problem with the goods or they are not what I requested, can I return them?</h3>
		<p>While it is possible, we like to avoid the hassle.  So, please make sure you want an item before you accept it.  There 
		are many ways to do this such as adding detail to your wish list or connecting with the donor to ask questions.</p>
		
		<h3>How do I register to donate items through giveNkind?</h3>
		<p>Membership is completely free and you can register here.</p>
		
		<h3><a id="taxline">Can I receive a tax receipt for my donation?</a></h3>
		<p>Yes! Only 501(c)(3) nonprofits can register with giveNkind, which means that every donation you make with us can get a 
		tax receipt.Please ask for a receipt when you make a donation!</p>
		
		<h3>What kind of items can be donated?</h3>
		<p>All types of items!
		To truly understand what we mean, take the site for a test drive. Use the search function to see what kids of items 
		are needed in your area.  You may find that you have what nonprofits need!</p>
		
		<h3>Are there any restrictions on what be donated?</h3>
		<p>We ask that items be in good quality or better and legal to use. Other than that, the sky’s the limit!</p>
   		
   		<h3>What does the “impact” section listed next to the nonprofit wish list items?</h3>
   		<p>We want donors to know about the good things their donation will do!  Nonprofits can tell you just how your donation
   		will impact their work in the community.</p>
   		
		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>
