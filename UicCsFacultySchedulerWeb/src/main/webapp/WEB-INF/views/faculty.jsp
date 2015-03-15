<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>

<!-- Template from: http://getbootstrap.com/examples/navbar-static-top/ -->
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!--link rel="icon" href="../../favicon.ico"-->

<title>Faculty</title>

<!-- Bootstrap core CSS -->
<!-- ${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/navbar-fixed-top.css"
	rel="stylesheet">

<script
	src="${pageContext.request.contextPath}/resources/bootstrap/js/ie-emulation-modes-warning.js"></script>

</head>

<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-static-top">
		<jsp:include page="navbar.jsp" />
	</nav>

	<div class="container">

		<!-- Main component for a primary marketing message or call to action -->
		<h1 class="page-header"><strong>Faculty</strong></h1>
		<div>
			<c:forEach var="instructor" items="${instructors}">
				<h3>${instructor.name}</h3>
				<a href="mailto:${instructor.email}">${instructor.email}</a>
				<p><span style="font-weight: bold;">Interests:</span> ${instructor.background}</p>
				<br>
			</c:forEach>
		</div>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
		  $('.facultyMenuItem').addClass('active');
		});
	</script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script-->
</body>
</html>
