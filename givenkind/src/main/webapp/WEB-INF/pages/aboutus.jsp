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
<title>Home</title>
</head>
<body id="grayBackground">

<%@ include file="nav.jsp" %>
<div id='blankspace'></div>
<div id='mainContainer' class='container-fluid'>
	<div id='gnk-aboutus' class='row'>
		<div class='col-sm-offset-2 col-sm-8'>
			<h2 class='aboutus-header'>ABOUT US</h2>
			<p class='aboutus-content'>giveNkind is a Chicago-based nonprofit organization dedicated to re-imagining giving to maximize the impact of a 
			charitable gift for both donors and nonprofits.  Founded by Emily Petway and a volunteer board of directors in 2012, 
			giveNkind exists to support small to medium sized charities that lack the infrastructure to develop an in-kind donation 
			exchange platform of their own, and donors looking for the opportunity to make a personal connection through the purposeful 
			donation of their gently used goods.  Through our simple and flexible online service, we offer a quintessential matching 
			system that helps create mutually beneficial connections directly between donors and nonprofits.</p>
			
			<h2 class='aboutus-header'>Our Vision</h2>
			<p class='aboutus-content'>The vision of the giveNkind organization is to support nonprofits in bettering the world.<br></p>
			
			<h2 class='aboutus-header'>Our Mission</h2>
			<p class='aboutus-content'>At giveNkind, our mission is to make giving more personal and purposeful.  We do this by providing a searchable, online 
			database that creates opportunities for individuals with items to donate to find, and directly connect with, registered 
			nonprofits that can re-purpose those items for good within their community.
			giveNkind is a registered 501(c)(3) organization.  View our <a href="<c:url value="/pdf/501c3_determination_letter.pdf"/>" target="_blank">IRS determination letter here</a>.  For a quick overview of 
			giveNkind, our mission and board member contact information, download our giveNkind Summary information page.  
			Please contact us at info@givenkind.org or (847) 802-8799 for more information.</p>

			<h2 class='aboutus-header'>The giveNkind Story</h2>
			<p class='aboutus-content'>After founding the Atlanta chapter of Becca’s Closet in 2009, giveNkind founder Emily Petway quickly discovered that, in order 
				to fulfill the organization’s mission of providing prom dresses to high school girls without the means to buy them, she would need 
				much more than dresses.  Her “wish list” included things like clothing racks, shoe racks, mirrors, chairs and curtains – many of 
				which she did not have the budget to afford.  Petway turned to the media to spread the word about the items she needed to support 
				her local community.  When people contacted her to donate, many said they had been searching for a long time to find a good cause 
				to donate their items to, and thus, her dream of creating an easier way to help nonprofits extend their community reach was born.
				In 2012, Petway moved to Gurnee, Illinois and founded giveNkind, a nonprofit organization dedicated to connecting individuals and 
				businesses with items to give with the nonprofits that needed them using a free, online platform.  By 2014 the organization had 
				grown to include nine volunteer and advisory board members.  In February of that year, the IRS accepted giveNkind as a 501(c)(3) 
				nonprofit organization and her dream came step closer to reality.  
				Over the next 2 and a half years, the giveNkind team worked with several partners to bring Petway’s vision to life.  In June of 
				2015, the website officially launched.  There are several organizations across the country that help facilitate in-kind donations 
				between donors and recipients, but giveNkind is the first to offer these services specifically for 501(c)(3) registered organizations, 
				using a free online platform on a nationwide scale.  By providing a searchable database of items available for donation and nonprofit 
				wish lists and facilitating direct communication between donors with items to give and nonprofits in need, giveNkind makes the donation 
				process more purposeful and personal.</p>
			
			<h1 class='aboutus-header'>Board of Directors</h1>
			<div class="border-description">
		    	<img class="aboutus-profile-img" src="<c:url value="/img/emily.jpg"/>"/>
		    	<h2 class='aboutus-header'>Emily Petway, President</h2>
				<p class='aboutus-content'>Emily Petway is the founder and president of giveNkind. Petway is an active leader in the nonprofit community and, most recently, founded and managed the Atlanta 
					Chapter of Becca’s Closet, a nationwide nonprofit that provides girls in need with formalwear for special events. During her tenure at Becca’s Closet she identified 
					that there is was no resource available to her agency, or other nonprofits, to communicate the “wish lists” of items needed to operate with the people and local 
					businesses able to donate them. She set out to make purposeful giving possible. Petway holds degrees in music education from Northwestern University and the University 
					of Michigan.  She has been awarded a number of grants and accolades for nonprofit work, including the Illinois State Parks and Recreation Volunteer of the Year Award. 
					Petway currently serves as the Certified Professional Development Units Coordinator for the Illinois Music Education Association.</p>
			</div>
			<div class="border-description">
				<img class="aboutus-profile-img" src="<c:url value="/img/natalie.jpg"/>"/>
				<h2 class='aboutus-header'>Natalie Keable, Vice President</h2>
				<p class='aboutus-content'>Natalie Keable is a Senior Consultant at Clerestory Consulting, LLC; a consulting firm that specializes in change management. Prior to working at Clerestory, 
					she was employed as a Senior Manager for the largest student-run, for-profit consultancy in the U.S., completing projects with clients, ranging from nonprofits to 
					Fortune 500 organizations.  Keable has experience in the agriculture, insurance, manufacturing, nonprofit, retail, sustainability and technology industries Her 
					expertise includes business plan development, training material development, training facilitation, business requirements definition, business process design and 
					documentation and brand awareness generation. She holds a master’s degree in business administration, with concentrations in Strategic Management and Marketing, 
					and a bachelor’s degree in Architectural Studies from the University of Illinois, Urbana-Champaign.</p> 
			</div>
			<div class="border-description">
				<img class="aboutus-profile-img" src="<c:url value="/img/patrick.png"/>"/>
				<h2 class='aboutus-header'>Patrick Swartzer, Treasurer</h2>
				<p class='aboutus-content'> Patrick currently works as an analyst at The Intercontinental Exchange in the Market Regulation Department. In the past, he clerked at law firms specializing 
					in litigation and public interest. While in law school, he took an interest in business development and regulation, expanding on his undergraduate education in 
					Economics. He moved to Chicago in the Spring of 2012 after completing studies for a law degree at Hamline University School of Law. Interested in putting this 
					education into practice, upon admission to the Illinois Bar, Patrick joined giveNkind in September of 2012 as a board member.</p>   
			</div>
			<div class="border-description">
				<h2 class='aboutus-header'>Pranav Parekh</h2>
				<p class='aboutus-content'>As Director of Information Technology, Pranav oversees and manages the technical, network and application framework of the company.  He directs the company’s 
					strategies for building out the patented software applications, enhancing network infrastructure, and ensuring that technology platforms scale with the company’s 
					continued growth. Pranav has worked at a variety of small businesses and startups over the past ten years, focusing primarily on rapidly expanding companies where 
					technology has been a driving factor in their success.  He uses his experience and skills to adopt business resources to current technology, and ensure that technology 
					works for business.  Pranav received his Bachelors in Finance and MIS from DePaul University, Chicago</p>
			</div>
			<div class="border-description">
				<img class="aboutus-profile-img" src="<c:url value="/img/JenG.jpg"/>"/>
				<h2 class='aboutus-header'>Jen Greco</h2>
				<p class='aboutus-content'>Jen Greco has over 15 years of marketing and design experience. She is currently a graphic designer working with clients in the entertainment, food and housewares 
					industries. Jen began her career in advertising helping to define media environments and efficiencies for clients such as JCPenney, Kmart, Sears, Cottonelle, Kleenex 
					and Visa. After 5 years at advertising agencies, Jen moved into a digital marketing role developing creative concepts for branded entertainment programs. It was here 
					that she started to explore and build her design skill set using creative solutions to solve business challenges. Jen graduated from Indiana University with a degree 
					in Business Marketing. She has completed coursework at Harrington College of Design and Chicago Portfolio School. Jen lives in Chicago with her husband and son.</p>
			</div>
			<h1 class='aboutus-header'>Advisory Board</h1>			
			<div class="border-description">
				<img class="aboutus-profile-img" src="<c:url value="/img/alan.jpg"/>"/>
				<h2 class='aboutus-header'>Alan Cruz</h2>
				<p class='aboutus-content'>Alan Cruz is a Texas native who has been a software developer/project manager for the DoD for over 8 years. He is received his bachelors degree in computer sciences 
					from Troy University and is working on his Masters in Cyber Security from Liberty University. He is the father to a rambunctious son in elementary school.  He spends 
					his spare time hiking the Colorado Rockies or playing at the playground with his son.</p>
			</div>			
			<div class="border-description">
				<img class="aboutus-profile-img" src="<c:url value="/img/ben.jpg"/>"/>
				<h2 class='aboutus-header'>Benjamin Somberg</h2>
				<p class='aboutus-content'>Benjamin L. Somberg received his bachelor’s degree from Duke University and a Ph.D. in experimental psychology from the University of Missouri—Columbia.  For over 
					thirty years, he worked as a human factors psychologist at a number of companies, including AT&amp;T Bell Laboratories, General Electric, and Medtronic.  In this role he 
					was responsible for the design and evaluation of user interfaces for products and services.  Ben retired from industry in 2011 and is currently on the adjunct faculty 
					in psychology at Metropolitan State University. He is the holder of seven U.S. patents, the author of numerous technical papers, and a member emeritus of the Human 
					Factors and Ergonomics Society.</p>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>
