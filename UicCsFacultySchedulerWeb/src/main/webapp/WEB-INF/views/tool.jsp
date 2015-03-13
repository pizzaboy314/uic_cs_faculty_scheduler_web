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
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/navbar-fixed-top.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/tool-search.css" rel="stylesheet">
    
	<link href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.css' rel='stylesheet' />
	<link href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
	<link href="${pageContext.request.contextPath}/resources/css/fullc.css" rel="stylesheet">
	
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/lib/moment.min.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/lib/jquery.min.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/lib/jquery-ui.custom.min.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.min.js'></script>
	<script src="${pageContext.request.contextPath}/resources/js/fullc.js"></script>
	
  </head>

  <body>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Course Scheduling Tool</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.html">Home</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="contact.html">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Tools<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="tool.html">Schedule Classes</a></li>
                <li><a href="usermanagement.html">Add User</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
            <div>
	        	<form class="navbar-form navbar-right">
		            <button type="submit" class="btn btn-success">Logout</button>
		        </form>
          	</div>
          	</li>
          </ul>
        </div><!--/.nav-collapse -->
        
      </div>
    </nav>
    
    <div class="container">
        <h1>Scheduling Tool</h1>
        		<table style="width:100%; border:10px, solid black">
					<tr>
						<td >
							<div class="box">
								<div class="container-1">
									<span class="icon"><i class="fa fa-search"></i></span>
									<input type="search" id="classsearch" placeholder="Search..." />
								<div>
							</div>
						</td>
						<td>				
							<div id='calendar'></div>
							<!-- embed src="fullcalendar/demos/external-dragging.html" -->
							<!-- -->	
						</td>
						<td>
							<div id='external-events'">
								<h4>Courses</h4>
									<div class='fc-event'>CS 111</div>
									<div class='fc-event'>CS 141</div>
									<div class='fc-event'>CS 151</div>
									<div class='fc-event'>CS 211</div>
									<div class='fc-event'>CS 301</div>
								<p>
									<input type='checkbox' id='drop-remove' />
									<label for='drop-remove'>remove after drop</label>
								</p>
							</div>
					</tr>
				</table>
    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!--script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script-->
  </body>
</html>
