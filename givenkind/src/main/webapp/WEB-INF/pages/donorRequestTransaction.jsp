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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/css/bootstrap-theme.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/js/jquery-ui-1.11.2/jquery-ui.min.css" />">
<link rel="stylesheet" href="<c:url value="/css/givenkind.css" />">
<title>Your Active Transactions</title>
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id='blankspace'></div>
	<div id="mainContainer" class="container">
		<div class='row'>
			<div class='col-sm-12'>
				<h1>Your Active Transactions</h1>
				<h3 style="color:red;">Transaction successfully added!</h3>
				<table class='table'>
					<thead>
						<tr>
							<th class='col-sm-2'>Item Name </th>
							<th class='col-sm-2'>Status </th>
							<th class='col-sm-2'>Nonprofit Info</th>
							<th class='col-sm-2'>Actions</th>
						</tr>
					</thead>
					<tbody>
						<!--Sample fields; Delete this section and replace with actual user data once implemntation is complete -->
						<tr>
							<td>Item 1</td>
							<td>Posted 2/14/2016</td>
							<td>NP 1</td>
							<td><a href=/givenkind/donorTransactions#>Delete</a></td>
						</tr>

						<tr>
							<td>Item 2</td>
							<td>Requested 2/16/2016</td>
							<td>NP 2</td>
							<td><a href=/givenkind/donorTransactions#>Accept</a> | <a href=/givenkind/donorTransactions#>Reject</a></td>
						</tr>

						<tr>
							<td>Item 2.5</td>
							<td>Accepted 2/19/2016</td>
							<td>NP 8</td>
							<td><a href=/givenkind/donorTransactions#>Confirm Shipment</a> | <a href=/givenkind/donorTransactions#>Cancel</a></td>
						</tr>

						<tr>
							<td>Item 3</td>
							<td>In Transit 2/19/2016</td>
							<td>NP 8</td>
							<td></td>
						</tr>

						<!-- End sample fields -->
					</tbody>
				</table>
			</div>
		</div>			
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
