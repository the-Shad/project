<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/t.tld" prefix="t"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>
<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/fontawesome.css">
<link rel="stylesheet" href="/static/css/my.css">

<title><p:message key="${pageTitle}" /></title>
</head>
<body>
	<header class="header-class">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="/home">WebApp</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<t:if test="${currentUser!=null && currentUser.active}">
						<t:if test="${IS_ADMIN}">
							<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/admin">
								<b><p:message key="main.page.admin"/></b>
							</a>
						</t:if>
						<t:if test="${IS_ADVTUTOR}">
							<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/advtutor">
								<b><p:message key="main.page.advtutor"/></b>
							</a>
						</t:if>
						<t:if test="${IS_TUTOR}">
							<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/tutor">
								<b><p:message key="main.page.tutor"/></b>
							</a>
						</t:if>
						<t:if test="${IS_STUDENT}">
							<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/student">
								<b><p:message key="main.page.student"/></b>
							</a>
						</t:if>
					</t:if>


				</div>
			</div>
			<form class="form-inline">
				<t:if test="${currentUser != null}">
					<a class="btn btn-outline-danger my-2 my-sm-0 mar" href="/logout">
						<b><p:message key="main.page.logout"/></b>
					</a>
				</t:if>
				<t:if test="${currentUser == null}">
					<a class="btn btn-outline-primary my-2 my-sm-0 mar" href="/login">
						<b><p:message key="main.page.login"/></b>
					</a>
					<a class="btn btn-outline-success my-2 my-sm-0 mar" href="/registration">
						<b><p:message key="main.page.signup"/>Sign Up</b>
					</a>
				</t:if>
			</form>
		</nav>
	</header>
	<div class="container" style="min-height: 550px">
		<jsp:include page="${currentPage}"></jsp:include>
	</div>

	<footer class="footer-class bg-dark" style="min-height: 100px">
		<div class="btn-group dropup">
			<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<p:message key="main.page.language"/> </button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="?lang=en">English</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="?lang=ru">Русский</a>
			</div>
		</div>
	</footer>
	<script src="/static/js/popper.js"></script>
	<script src="/static/js/jquery-3.3.1.js"></script>
	<script src="/static/js/bootstrap.js"></script>

</body>
</html>