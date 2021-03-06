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

    <title>Contact Us</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/navbar-fixed-top.css" rel="stylesheet">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/ie-emulation-modes-warning.js"></script>

  </head>

  <body>

    <!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-static-top">
		<jsp:include page="navbar.jsp" />
	</nav>

    <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>Contact Us</h2>
        <div>
	        <p>Send an email to <a href="some1@example.com">some1@example.com</a> and we'll
	        get back to you as soon as we can.</p>
        </div>
      </div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
		  $('.contactMenuItem').addClass('active');
		});
	</script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!--script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script-->
  </body>
</html>
