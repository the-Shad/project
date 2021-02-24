<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div style="margin-bottom: 10px;">
	<form action="/student" method="GET" class="form-inline">
		<input type="number" name="page" value="${CURRENT_PAGE}" min="1" max="${MAX_PAGE}" step="1">
		<button type="submit" class="btn btn-primary" style="margin: 10px;">
			<p:message key="btn.go" />
		</button>
		<input class="form-control mr-sm-2" name="q" placeholder="Search">
		<a class="btn btn-primary" style="margin: 10px;" href="/student/results">
			<p:message key="btn.results" />
		</a>
	</form>
</div>
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">ID</th>
			<th scope="col" style="width:70%"><p:message key="student.page.name" /></th>
			<th scope="col"><p:message key="student.page.description" /></th>
			<th scope="col"><p:message key="student.page.actions" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="test" items="${LIST}">
			<tr>
				<th scope="row">${test.id}</th>
				<td>${test.name}</td>
				<td>
					
				</td>
				<td>
					<form action="/student" method="POST">
						<input type="number" value="${test.id}" name="test_id" hidden="true">
						<button type="submit" class="btn btn-success">
							<b><p:message key="student.page.start" /></b>
						</button>
					</form>
				</td>

			</tr>
		</c:forEach>
	</tbody>
</table>