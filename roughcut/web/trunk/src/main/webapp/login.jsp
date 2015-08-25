<link rel="stylesheet" href="resources/css/signin.css">

<div class="container">
	<form class="form-signin" role="form" method="post" action="${pageContext.servletContext.contextPath}/j_spring_security_check">
		<h2 class="form-signin-heading">Please sign in</h2>
		<input name="j_username" type="email" class="form-control" placeholder="Email address" required autofocus>
		<input name="j_password" type="password" class="form-control" placeholder="Password" required>
		<label class="checkbox"><input type="checkbox" value="remember-me"> Remember me</label>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	</form>
</div>
<!-- /container -->
