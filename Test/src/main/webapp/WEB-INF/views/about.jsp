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

<title>About</title>

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
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Course Scheduling Tool</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.html">Home</a></li>
					<li><a href="about.html">About</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<div>
							<form class="navbar-form navbar-right">
								<div class="form-group">
									<input type="text" placeholder="Username" class="form-control">
								</div>
								<div class="form-group">
									<input type="password" placeholder="Password"
										class="form-control">
								</div>
								<button type="submit" class="btn btn-success">Sign in</button>
							</form>
						</div>
					</li>
				</ul>
			</div>
			<!--/.nav-collapse -->

		</div>
	</nav>

	<div class="container">

		<!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h2>About</h2>
			<p>This is a project developed under CS 422 to help Professor
				Troy create the course schedules per semester</p>
		</div>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!--script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script-->
</body>
</html>
