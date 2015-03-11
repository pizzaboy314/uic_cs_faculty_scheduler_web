<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="navbardiv" class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="">Course Scheduling Tool</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="">Home</a></li>
			<li><a href="about">About</a></li>
			<li><a href="contact">Contact</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<div>
					<c:choose>
	      				<c:when test="${empty cookie['LoggedIn']}">
							<form action="LoginServlet" method="post" class="navbar-form navbar-right">
								<div class="form-group">
									<input type="text" name="user" placeholder="Username" class="form-control">
								</div>
								<div class="form-group">
									<input type="password" name="pwd" placeholder="Password"
										class="form-control">
								</div>
								<button type="submit" class="btn btn-success">Sign in</button>
							</form>
						</c:when>
						<c:otherwise>
							<form class="navbar-form navbar-right">
								<button type="submit" class="btn btn-success">Logout</button>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</li>
		</ul>
	</div>
</div>