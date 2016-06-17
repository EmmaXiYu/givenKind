<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default navbar-fixed-top">



	<div class='container-fluid'>

        <sec:authorize access="isAnonymous()">
        <div id='gnk-navbar-center-anon' class='collapse navbar-collapse'>
            <ul class='nav navbar-nav'>
                <li><a href="<c:url value="/"/>">Home</a></li>
                <li><a href="<c:url value="/about"/>">About</a></li>
                <li><a href="<c:url value="/faq"/>">FAQ</a></li>
            </ul>
            <a class='navbar-brand' href="<c:url value="/"/>" style="padding: 0px 25px;" >
                <img id="logo" src="<c:url value="/img/giveNkind.circle.png"/>" />
            </a>
            <ul class='nav navbar-nav'>
                <li><a href="<c:url value="/contact"/>">Support Us</a></li>
                <li><a href="<c:url value="/login"/>">Login</a></li>
            </ul>
        </div>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
		<div id='gnk-navbar-center-auth' class='collapse navbar-collapse'>
            <ul class='nav navbar-nav'>
                <li><a href="<c:url value="/"/>">Home</a></li>
                <li><a href="<c:url value="/about"/>">About</a></li>
		        <li><a href="<c:url value="/faq"/>">FAQ</a></li>
                <li><a href="<c:url value="/contact"/>">Support Us</a></li>
                <li><a href="<c:url value="/search"/>">Search</a></li>
		    </ul>
		    <a class='navbar-brand' href="<c:url value="/"/>" style="padding: 0px 25px;" >
				<img id="logo" src="<c:url value="/img/giveNkind.circle.png"/>" />
			</a>
		    <ul class='nav navbar-nav'>
                <sec:authorize access="hasRole('ROLE_NONPROFIT')">
                <li><a href="<c:url value="/wishlist"/>">My Wish List</a></li>
                </sec:authorize>
                 <sec:authorize access="hasRole('ROLE_DONOR')">
                <li><a href="<c:url value="/donorlist"/>">My Donation List</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="<c:url value="/admin/view"/>">Admin</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_NONPROFIT')">
                <li><a href="<c:url value="/nonprofit"/>">My Profile</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_DONOR')">
                <li><a href="<c:url value="/donor"/>">My Profile</a></li>
                </sec:authorize>
                <li><a href="<c:url value="/logout"/>">Logout</a></li>
		     </ul>
		</div>
        </sec:authorize>

	</div>
</nav>