<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>

<!-- Template from: http://getbootstrap.com/examples/navbar-static-top/ -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!--link rel="icon" href="../../favicon.ico"-->

<title>Scheduling Tool</title>

<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/navbar-fixed-top.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/tool-search.css"
	rel="stylesheet">

<link
	href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.css'
	rel='stylesheet' />
<link
	href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.print.css'
	rel='stylesheet' media='print' />
<link href="${pageContext.request.contextPath}/resources/css/fullc.css"
	rel="stylesheet">

<script
	src='${pageContext.request.contextPath}/resources/fullcalendar/lib/moment.min.js'
	type="text/javascript"></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar/lib/jquery.min.js'
	type="text/javascript"></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar/lib/jquery-ui.custom.min.js'
	type="text/javascript"></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.min.js'
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/fullc.js"
	type="text/javascript"></script>
</head>


<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-static-top">
		<jsp:include page="navbar.jsp" />
	</nav>

	<div class="container">
		<h1>Scheduling Tool</h1>
		<table style="width: 100%; border: 10px, solid black">
			<tr>
				<!-- The Drag/Drop Box -->
				<td>
					<div class="box">
						<div class="container-1">
							<input type="search" id="classsearch" placeholder="Search..." />
							<span class="icon"><i class="glyphicon glyphicon-search"
								style="margin-left: -30px; color: gray;"></i></span>
						</div>
					</div>
					<p></p>
					<div id='external-events'
						style="height: 400px; overflow-y: scroll;">
						<h4 style="text-align: center;">Drag Courses to Schedule</h4>
						<c:forEach var="course" items="${courses}">
							<div class='fc-event' data-toggle="tootip" title="${course.NAME}">
								CS ${course.NUMBER}</div>
						</c:forEach>
					</div>
				</td>
				<!-- The Drag/Drop Box -->
				<td>
					<div id='calendar'></div> <!-- embed src="fullcalendar/demos/external-dragging.html" -->
					<!-- -->
				</td>
				<!-- Tools -->
				<td>
					<button type="button" class="btn btn-default btn-lg"
						style="text-align: center;">Clear</button>
					<p></p>
					<button type="button" class="btn btn-default btn-lg">Redo</button>
					<p></p>
					<button type="button" class="btn btn-default btn-lg">Undo</button>
					<p></p>
					<button type="button" class="btn btn-default btn-lg"
						style="text-align: center;">Save</button>
					<p></p>
					<div id='external-events'
						style="height: 200px; overflow-y: scroll;">
						<h4 style="text-align: center;">Trash Can</h4>
						<p>To remove courses from schedule, drag into this area.</p>
						<span class="icon"> <i class="glyphicon glyphicon-trash"
							style="margin-left: 55px; color: gray;"></i>
						</span>
					</div>
				</td>
				<!-- Tools -->
			</tr>
		</table>
	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script-->
</body>
</html>
