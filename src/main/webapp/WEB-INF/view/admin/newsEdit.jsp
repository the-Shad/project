<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>


<div class="container" style="width: 95%;">
	<a href="/home" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card">
		<div class="card-header">
			<p:message key="home.page.editForm" />
		</div>
		<form action="/admin/news/edit" method="POST">
			<div class="card-body">
				<input name="news_id" value="${news.id}" hidden="true">
				<label>
					<p:message key="home.page.name" />
				</label>
				<input type="text" class="form-control" name="name" value="${news.name}">
				<hr>
				<div style="margin: 10px">
					<label>
						<p:message key="home.page.news" />
					</label>
					<br>
					<div style="width: 100%; margin-left: auto; margin-right: auto"></div>
					<textarea name="news_text" class="form-control" style="width: 100%; height: 150px;">${news.news}</textarea>
				</div>
				<button type="submit" class="btn btn-success">
					<p:message key="home.page.save" />
				</button>
			</div>
		</form>
	</div>
</div>