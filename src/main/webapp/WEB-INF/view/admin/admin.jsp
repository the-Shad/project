<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>


<div style="margin-bottom: 10px;">
	<form action="/admin" method="GET" class="form-inline">
		<input type="number" name="page" value="${CURRENT_PAGE}" min="1" max="${MAX_PAGE}" step="1">
		<button type="submit" class="btn btn-primary" style="margin: 10px;">
			<p:message key="btn.go" />
		</button>
		<input class="form-control mr-sm-2" name="q" placeholder="Search">
		<a href="/admin/create" class="btn btn-primary">
			<p:message key="btn.createUser" />
		</a>
	</form>
</div>
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">ID</th>
			<th scope="col"><p:message key="admin.page.name" /></th>
			<th scope="col"><p:message key="admin.page.login" /></th>
			<th scope="col" style="width:10%"><p:message key="admin.page.active" /></th>
			<th scope="col"><p:message key="admin.page.actions" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="user" items="${LIST}">
			<tr>
				<th scope="row">${user.id}</th>
				<td>${user.fullName}</td>
				<td>${user.login}</td>
				<td><c:if test="${user.active}">
						<div class="btn btn-success"  style="width:100%">
							<b><p:message key="admin.page.true" /></b>
						</div>
					</c:if> <c:if test="${not user.active}">
						<div class="btn btn-danger"  style="width:100%">
							<b><p:message key="admin.page.false" /></b>
						</div>
					</c:if></td>
				<td>
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<p:message key="admin.page.actions" />
						</button>
						<div class="dropdown-menu">
							<form class="dropdown-item" action="/admin/edit" method="GET">
								<input type="number" value="${user.id}" name="user_id" hidden="true">
								<button type="submit" class="btn btn-outline-primary">
									<b><p:message key="btn.edit" /></b>
								</button>
							</form>
							<form class="dropdown-item" action="/admin" method="POST">
								<input type="number" value="${user.id}" name="user_id" hidden="true">
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