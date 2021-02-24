<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div style="margin-bottom: 10px;">
	<form action="?" method="GET" class="form-inline">
		<input type="number" name="page" value="${CURRENT_PAGE}" min="1" max="${MAX_PAGE}" step="1">
		<button type="submit" class="btn btn-primary" style="margin: 10px;">
			<p:message key="btn.go" />
		</button>
		<input class="form-control mr-sm-2" name="q" placeholder="Search">
		<a href="${requestURI}/results" class="btn btn-primary" style="margin: 10px;">
			<p:message key="btn.results" />
		</a>
		<c:if test="${IS_TUTOR}">
			<a href="/tutor/create" class="btn btn-primary" style="margin: 10px;">
				<p:message key="tutor.page.createTest" />
			</a>
		</c:if>

	</form>
</div>
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">ID</th>
			<th scope="col" style="width: 70%"><p:message key="tutor.page.name" /></th>
			<th scope="col"><p:message key="tutor.page.description" /></th>
			<th scope="col" style="width: 15%"><p:message key="tutor.page.actions" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="test" items="${LIST}">
			<tr>
				<th scope="row">${test.id}</th>
				<td>${test.name}</td>
				<td>
					<div class="dropdown dropleft" style="width: 300%;">
						<button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<p:message key="tutor.page.description" />
						</button>
						<div class="dropdown-menu" style="width: 200%;">
							<div style="margin: 5px">
								<c:if test="${IS_ADVTUTOR}">
									<b><p:message key="tutor.page.author"/>:</b>${test.authorId}<hr>
								</c:if>
								${test.description}
							</div>
						</div>
					</div>
				</td>
				<td>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<p:message key="tutor.page.actions" />
						</button>
						<div class="dropdown-menu">
							<form class="dropdown-item" action="/tutor/edit" method="GET">
								<input type="number" value="${test.id}" name="test_id" hidden="true">
								<button type="submit" class="btn btn-outline-primary">
									<b><p:message key="btn.edit" /></b>
								</button>
							</form>
							<form class="dropdown-item" method="POST">
								<input type="number" value="${test.id}" name="test_id" hidden="true">
								<button type="submit" class="btn btn-outline-danger">
									<b><p:message key="btn.delete" /></b>
								</button>
							</form>
						</div>
					</div>
				</td>

			</tr>
		</c:forEach>
	</tbody>
</table>