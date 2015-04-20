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
						style="height: 400px; overflow-y: scroll; overflow-x: hidden; width: auto;">
						<h4 style="text-align: center;">Drag Courses to Schedule</h4>
						<c:forEach var="course" items="${courses}">
							<c:choose>
								<c:when test="${course.NUMBER < 200}">
									<div class='fc-event' style='background-color: ${pre200BGColor}; color: ${pre200TXColor};' data-toggle="tootip" title="${course.NAME}">
										CS ${course.NUMBER}
									</div>
								</c:when>
								<c:when test="${course.NUMBER < 300}">
									<div class='fc-event' style='background-color: ${pre300BGColor}; color: ${pre300TXColor};' data-toggle="tootip" title="${course.NAME}">
										CS ${course.NUMBER}
									</div>
								</c:when>
								<c:when test="${course.NUMBER < 400}">
									<div class='fc-event' style='background-color: ${pre400BGColor}; color: ${pre400TXColor};' data-toggle="tootip" title="${course.NAME}">
										CS ${course.NUMBER}
									</div>
								</c:when>
								<c:when test="${course.NUMBER < 500}">
									<div class='fc-event' style='background-color: ${pre500BGColor}; color: ${pre500TXColor};' data-toggle="tootip" title="${course.NAME}">
										CS ${course.NUMBER}
									</div>
								</c:when>
								<c:otherwise>
									<div class='fc-event' style='background-color: ${defaultBGColor}; color: ${defaultTXColor};' data-toggle="tootip" title="${course.NAME}">
										CS ${course.NUMBER}
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</td>
				<!-- The Drag/Drop Box -->
				<td>
					<button id='options' type="button" class="btn btn-default btn-lg"
						style="text-align: center; color: white; background-color: #428bca; font-size: 1.2em;">Clear</button>
					
					<button type="button" class="btn btn-default btn-lg"
						style="text-align: center; color: white; background-color: #f0ad4e; font-size: 1.2em;">Redo</button>
					
					<button type="button" class="btn btn-default btn-lg"
						style="text-align: center; color: white; background-color: #d9534f; font-size: 1.2em;">Undo</button>
					
					<button type="button" class="btn btn-default btn-lg"
						style="text-align: center; color: white; background-color: #5cb85c; font-size: 1.2em;">Save</button>
					<p></p>
					<div id='calendar'></div> <!-- embed src="fullcalendar/demos/external-dragging.html" -->
					<!-- -->
				</td>
				<!-- Tools -->
				<td>
				
					<div id='external-events' style="height: 200px; background-color: #eee ">
						<h4 style="text-align: center;">Trash Can</h4> 
						<p>To remove courses from schedule, drag into this area.</p>
						<span class="icon"> <i class="glyphicon glyphicon-trash"
							style="margin-left: 45px; color: gray; font-size: 2.2em;"></i>
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
	<script>
	
	</script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script-->
</body>
</html>
