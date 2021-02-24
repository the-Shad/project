<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>


<c:if test="${IS_ADMIN}">
	<div style="margin-left: auto; margin-right: auto; margin-top: 15px; margin-bottom: 15px">
		<a href="/admin/news/create" class="btn btn-success">
			<p:message key="home.page.create" />
		</a>
	</div>
</c:if>
<c:forEach var="news" items="${LIST}">
	<div class="card" style="margin: 10px">
		<div class="card-header">
			<b>${news.name}</b><small class="float-right">${news.created}</small>
		</div>
		<div class="card-body">
			<p>${news.news}</p>
		</div>
	</div>
	<c:if test="${IS_ADMIN}">
		<div class="row">
			<div class="col" style="margin: 10px">
				<form action="/admin/news/edit" method="GET">
					<input name="news_id" value="${news.id}" hidden="true">
					<button type="submit" class="btn btn-success">
						<p:message key="btn.edit" />
					</button>
				</form>
			</div>
			<div class="col" style="margin: 10px">
				<form action="/home" method="POST">
					<input name="news_id" value="${news.id}" hidden="true">
					<button type="submit" class="btn btn-danger">
						<p:message key="btn.delete" />
					</button>
				</form>
			</div>
		</div>
	</c:if>
	<hr />
</c:forEach>
<div style="margin-bottom: 10px;">
	<form action="/home" method="GET" class="form-inline">
		<input type="number" name="page" value="${CURRENT_PAGE}" min="1" max="${MAX_PAGE}" step="1">
		<button type="submit" class="btn btn-primary" style="margin: 10px;">
			<p:message key="btn.go" />
		</button>
	</form>
</div>