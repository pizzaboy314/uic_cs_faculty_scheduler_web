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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar/lib/jquery-ui.custom.min.js'
	type="text/javascript"></script>
<script
	src='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.min.js'
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/fullc.js"
	type="text/javascript"></script>
	
	
<style type="text/css">
	div.calendar-trash{
		float: left;
		padding-right: 8px;
		margin-right:5px;
		padding-left:8px;
		padding-top: 3px;
		cursor: pointer;
		color: red;
	}

	.to-trash{
  	background-color:red;
    -webkit-border-radius: 5em;
    border-radius: 5em;
	}
	
	#contextMenu {
  		position: absolute;
  		display:block;
  		float: left;
		width: 150px;
		padding: 0 10px;
		border: 1px solid #fffff;
		
		text-align: left;
		z-index:10001;
	}
</style>
</head>

<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-static-top">
		<jsp:include page="navbar.jsp" />
	</nav>
	<input type="hidden" id="DEFAULT_EVENT_LENGTH"
		value="${DEFAULT_EVENT_LENGTH}">
		
	<input type="hidden" id="DEFAULT_EVENT_LENGTH"
		value="${DEFAULT_EVENT_LENGTH}">

	<div class="container">
		<h1>Scheduling Tool</h1>
		<table style="width: 100%; border: 10px, solid black">
			<tr>
				<!-- The Drag/Drop Box -->
				<td>
					<form role="form">
						<div class="form-group has-feedback" style="width: 200px;">
							<input type="text" class="form-control" placeholder="Search..." />
							<i class="form-control-feedback glyphicon glyphicon-search"></i>
						</div>
					</form>
					<div id='external-events'
						style="height: 400px; overflow-y: scroll; overflow-x: hidden; width: auto;">
						<h4 style="text-align: center;">Drag Courses to Schedule</h4>
						<c:forEach var="course" items="${courses}">
							<c:choose>
								<c:when test="${course.NUMBER < 200}">
									<div class='fc-event'
										style='background-color: ${PRE200_COL_BG}; color: ${PRE200_COL_TX};'
										data-toggle="tootip" title="${course.NAME}">CS ${course.NUMBER}</div>
								</c:when>
								<c:when test="${course.NUMBER < 300}">
									<div class='fc-event'
										style='background-color: ${PRE300_COL_BG}; color: ${PRE300_COL_TX};'
										data-toggle="tootip" title="${course.NAME}">CS ${course.NUMBER}</div>
								</c:when>
								<c:when test="${course.NUMBER < 400}">
									<div class='fc-event'
										style='background-color: ${PRE400_COL_BG}; color: ${PRE400_COL_TX};'
										data-toggle="tootip" title="${course.NAME}">CS ${course.NUMBER}</div>
								</c:when>
								<c:when test="${course.NUMBER < 500}">
									<div class='fc-event'
										style='background-color: ${PRE500_COL_BG}; color: ${PRE500_COL_TX};'
										data-toggle="tootip" title="${course.NAME}">CS ${course.NUMBER}</div>
								</c:when>
								<c:otherwise>
									<div class='fc-event'
										style='background-color: ${DEFAULT_COL_BG}; color: ${DEFAULT_COL_TX};'
										data-toggle="tootip" title="${course.NAME}">CS ${course.NUMBER}</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<div id="contextMenu" class="dropdown-toggle">
							<ul class="dropdown-menu" role="menu"
								aria-labelledby="dropdownMenuDivider"
								style="display: table; position: static; margin-bottom: 5px;">
								<li><div style="margin-left: 10px"><strong>Course Info:</strong></div></li>
<!-- 								<li class="divider"></li> -->
								<li><div id="cnum" style="margin-left: 20px" ></div></li>
								<li><div style="margin-left: 20px">Name:</div></li>
								<li><div style="margin-left: 20px">Hours:</div></li>
								<li class="divider"></li>
								<li><a href="#">Edit</a></li>
								<li><a href="javascript: removeEvent()">Remove</a></li>
							</ul>
						</div>
					</div>
				</td>
				<!-- The Drag/Drop Box -->
				<td>
				<td>
					<button id='drop-remove' type="button" onclick="removeAll()" class="btn btn-default btn-lg"
						style="text-align: center; color: white; background-color: #428bca; font-size: 1.2em;">Clear</button>
						
					<button id='undo-opt' type="button" class="btn btn-default btn-lg"
						style="text-align: center; color: white; background-color: #d9534f; font-size: 1.2em;">Undo</button>
						
					<button type="button" class="btn btn-default btn-lg"
						style="text-align: center; color: white; background-color: #f0ad4e; font-size: 1.2em;">Redo</button>
						
					<div id='calendar'></div> <!-- embed src="fullcalendar/demos/external-dragging.html" -->
					<!-- -->
				</td>
				<!-- Tools -->
				<td>
					<div id='external-events'
						style="height: 200px; background-color: #eee">
						<h4 style="text-align: center;">Instructions</h4>
						<p>Drag courses from the left panel into the calendar.</p>
						<p>To remove or edit, right click the event.</p>
						
						<!--
						<span class="icon"> <i class="glyphicon glyphicon-trash"
							style="margin-left: 45px; color: gray; font-size: 2.2em;"></i>
						</span>
						-->
						
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
	$(document).ready(function() {
		$('.schedMenuItem').addClass('active');
		$("#contextMenu").hide();
	});
	</script>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script-->
</body>
</html>
