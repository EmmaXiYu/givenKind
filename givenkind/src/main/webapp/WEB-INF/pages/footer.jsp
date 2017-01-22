<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class='container-fluid'>
        <div class='collapse navbar-collapse'>
            <ul class='nav navbar-nav'>
                <li><a href="<c:url value="/tac"/>">Terms and Conditions</a></li>
            </ul>
            <sec:authorize access="isAuthenticated()">
            <ul id='footer-btn-row'>
            	<sec:authorize access="hasRole('ROLE_NONPROFIT')">
                	<li><a href="<c:url value="/wishlist"/>" role='button' class='btnFooter'>My Wish List</a></li>
              	</sec:authorize>
            	<sec:authorize access="hasRole('ROLE_DONOR')">
               		<li><a href="<c:url value="/donorlist"/>" role='button' class='btnFooter'>My Donation List</a></li>
              	</sec:authorize>
              	<sec:authorize access="hasRole('ROLE_ADMIN')">
                	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button">Admin</a>
		          		<ul class="dropdown-menu">
		           			<li><a href="<c:url value="/admin/view/donatedItem"/>">Donated Items</a></li>
		           			<li><a href="<c:url value="/admin/view/wishList"/>">Wish List</a></li>
		           			<li><a href="">Transaction</a></li>
		           			<li><a href="<c:url value="/admin/view/status"/>">User status</a></li>
		           			<li><a href="">User's profile</a></li>
		         		</ul>
   			    	</li>
               	</sec:authorize>
               	<sec:authorize access="hasRole('ROLE_NONPROFIT')">
                	<li><a href="<c:url value="/nonprofit"/>" role='button' class='btnFooter'>My Profile</a></li>
               	</sec:authorize>
               	<sec:authorize access="hasRole('ROLE_DONOR')">
                	<li><a href="<c:url value="/donor"/>" role='button' class='btnFooter'>My Profile</a></li>
               	</sec:authorize>
                	<li><a href="<c:url value="/search"/>" role='button' class='btnFooter'>Search</a></li>
		    </ul>
		   	</sec:authorize>
		              	              
            <ul class='nav navbar-nav navbar-right socialLinks'>
            	<li> <a href="https://www.facebook.com/givenkind"><i class="fa fa-facebook" aria-hidden="true" style="color:#3b5998"></i></a><li>
             	<li> <a href="https://twitter.com/givenkindorg"><i class="fa fa-twitter" aria-hidden="true" style="color:#55acee"></i></a><li>
             	<li> <a href="https://www.youtube.com/channel/UC776HLx66nQIAUZxxy5Kp_Q"><i class="fa fa-youtube" aria-hidden="true" style="color:#bb0000"></i></a><li>
             	<li> <a href="https://www.linkedin.com/company/3730790?trk=tyah&trkInfo=tarId%3A1400500514067%2Ctas%3Agivenkind%2Cidx%3A1-1-1"><i class="fa fa-linkedin" aria-hidden="true" style="color:#007bb5"></i></a><li>
            </ul>
        </div>
    </div>
</nav>
<script src=" <c:url value="/js/jquery-2.1.1.min.js"/> " /></script>
<script src=" <c:url value="/js/bootstrap.min.js"   /> "></script>
