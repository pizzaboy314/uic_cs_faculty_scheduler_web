<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="${pageContext.request.contextPath}/resources/css/logo.css"
	rel="stylesheet">
<div id="navbardiv" class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<div>
		<a class="navbar-brand" href="${pageContext.request.contextPath}">
			<img  alt="UIC" style="max-width:100%; height:50px;" src="${pageContext.request.contextPath}/resources/img/UICsquare.svg">
		</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="facultyMenuItem"><a href="${pageContext.request.contextPath}/faculty">Faculty</a></li>
			<li class="coursesMenuItem"><a href="${pageContext.request.contextPath}/courses">Courses</a></li>
			<li class="aboutMenuItem"><a href="${pageContext.request.contextPath}/about">About</a></li>
			<li class="contactMenuItem"><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
			<c:choose>
				<c:when test="${not empty cookie['LoggedIn']}">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Tools<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="tool.html">Schedule Classes</a></li>
                <li><a href="usermanagement.html">Add User</a></li>
              </ul>
            </li>
				</c:when>
			</c:choose>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<div>
					<c:choose>
	      				<c:when test="${empty cookie['LoggedIn']}">
							<form action="${pageContext.request.contextPath}/login" method="post" class="navbar-form navbar-right">
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
							<form action="${pageContext.request.contextPath}/logout" method="post" class="navbar-form navbar-right">
								<button type="submit" class="btn btn-success">Logout</button>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</li>
		</ul>
	</div>
</div>