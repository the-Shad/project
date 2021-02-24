<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>


<div class="container" style="width: 95%;">
	<a href="/tutor" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card">
		<div class="card-header">
			<p:message key="tutor.page.createTestForm" />
		</div>
		<form action="/tutor/create" method="POST">
			<div class="card-body">
				<label>
					<p:message key="tutor.page.name" />
				</label>
				<input type="text" class="form-control" name="test_name" value="${NAME}">
				<hr>
				<div style="margin: 10px">
					<label>
						<p:message key="tutor.page.description" />
					</label>
					<br>
					<div style="width: 100%; margin-left: auto; margin-right: auto">
						<textarea name="description" style="width: 100%; height: 150px;">${DESCRIPTION}</textarea>
					</div>
				</div>
				<button type="submit" class="btn btn-success">
					<p:message key="btn.save" />
				</button>
			</div>
		</form>
	</div>
</div>