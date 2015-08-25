<div role="navigation" class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="${pageContext.servletContext.contextPath}" class="navbar-brand">Rough Cut</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.servletContext.contextPath}">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
				<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" role="button" href="#" id="drop1">List<b class="caret"></b></a>
					<ul aria-labelledby="drop1" role="menu" class="dropdown-menu">
						<li role="presentation"><a href="${pageContext.servletContext.contextPath}/company/retrieve" tabindex="-1" role="menuitem">Companies</a></li>
<!-- 						<li class="divider" role="presentation"></li> -->
<!-- 						<li role="presentation"><a href="http://twitter.com/fat" tabindex="-1" role="menuitem">Separated link</a></li> -->
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown" id="fat-menu"><a data-toggle="dropdown" class="dropdown-toggle" role="button" id="drop3" href="#"><i class="glyphicon glyphicon-cog"></i> Preferences<b class="caret"></b></a>
					<ul aria-labelledby="drop3" role="menu" class="dropdown-menu">
						<li role="presentation"><a href="${pageContext.servletContext.contextPath}/j_spring_security_logout" tabindex="-1" role="menuitem"><i class="glyphicon glyphicon-off"></i> Logout</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>