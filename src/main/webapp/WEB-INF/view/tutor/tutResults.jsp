<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div style="margin-bottom: 10px;">
	<form action="?" method="GET" class="form-inline">
		<input type="number" name="page" value="${CURRENT_PAGE}" min="1" max="${MAX_PAGE}" step="1">
		<button type="submit" class="btn btn-primary" style="margin: 10px;">
			<p:message key="btn.go" />
		</button>
		<a href="${requestURI}" class="btn btn-primary" style="margin: 10px;">
			<p:message key="btn.back" />
		</a>
	</form>
</div>
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">ID</th>
			<th scope="col"><p:message key="tutor.page.name" /></th>
			<th scope="col"><p:message key="tutor.page.result.studentname" /></th>
			<th scope="col"><p:message key="tutor.page.result.result" /></th>
			<th scope="col" style="width: 5%"><p:message key="tutor.page.result.tidsid" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${LIST}">
			<tr>
				<th scope="row">${result.id}</th>
				<td>${result.testName}</td>
				<td>${result.studentName}</td>
				<td><b>${result.result}</b></td>
				<td><b>T:</b>${result.authorId}<br>
				<b>S:</b>${result.studentId}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>