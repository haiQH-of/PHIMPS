<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="/assm-user/">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
			<div class="navbar-brand">
				<a href="HomeAdmin" class="navbar-item"> 
					<img src="https://tinyzonetv.to/images/group_2/theme_1/logo.png?v=0.1" alt="">
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a href="${pageContext.request.contextPath}/admin/Homeadmin" class="nav-link">
							<i class="fas fa-home"></i> HOME
						</a>
					</li>
					<li class="nav-item">
						<a href="${pageContext.request.contextPath}/admin/video" class="nav-link">
							<i class="fas fa-video"></i> VIDEOS
						</a>
					</li>
					<li class="nav-item">
						<a href="${pageContext.request.contextPath}/admin/user" class="nav-link">
							<i class="fas fa-user-cog"></i> USERS
						</a>
					</li>
					<li class="nav-item">
						<a href="${pageContext.request.contextPath}/Report" class="nav-link">
							<i class="fas fa-chart-bar"></i> REPORTS
						</a>
					</li>
					<li class="nav-item">
						<a href="${pageContext.request.contextPath}/admin/logoff" class="nav-link">
							<i class="fas fa-sign-out-alt"></i> Log out
						</a>
					</li>
				</ul>
			</div>
		</nav>
	</header>

	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
