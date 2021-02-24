<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>
<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/fontawesome.css">
<link rel="stylesheet" href="/static/css/my.css">

<title><p:message key="${PAGE_TITLE}" /></title>
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
					<c:if test="${IS_ADMIN}">
						<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/admin">
							<b><p:message key="main.page.admin" /></b>
						</a>
					</c:if>
					<c:if test="${IS_ADVTUTOR}">
						<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/advtutor">
							<b><p:message key="main.page.advtutor" /></b>
						</a>
					</c:if>
					<c:if test="${IS_TUTOR}">
						<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/tutor">
							<b><p:message key="main.page.tutor" /></b>
						</a>
					</c:if>
					<c:if test="${IS_STUDENT}">
						<a class=" btn btn-outline-light mr-2" style="border: 0px" href="/student">
							<b><p:message key="main.page.student" /></b>
						</a>
					</c:if>
				</div>
			</div>
			<form class="form-inline">
				<c:if test="${CURRENT_USER != null}">
					<a class="btn btn-danger mar" href="/logout">
						<b><p:message key="main.page.logout" /></b>
					</a>
				</c:if>
				<c:if test="${CURRENT_USER == null}">
					<a class="btn btn-primary mar" href="/login">
						<b><p:message key="main.page.login" /></b>
					</a>
					<a class="btn btn-success mar" href="/registration">
						<b><p:message key="main.page.signup" /></b>
					</a>
				</c:if>
			</form>
		</nav>
	</header>
	<c:if test="${not empty ERROR_MESSAGE}">
		<div style="margin-left: auto; margin-right: auto">
			<button class="btn btn-outline-danger" disabled>
				<p:message key="${ERROR_MESSAGE}" />
			</button>
		</div>
	</c:if>
	<div class="container" style="min-height: 550px">
		<jsp:include page="${currentPage}"></jsp:include>
	</div>

	<footer class="footer-class bg-dark" style="min-height: 100px">
		<div class="btn-group dropup" style="margin: 10px">
			<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<p:message key="main.page.language" />
			</button>
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