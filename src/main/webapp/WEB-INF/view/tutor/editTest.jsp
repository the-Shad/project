<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="width: 95%;">
	<a href="/tutor" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card" style="margin: 10px">
		<div class="card-header">
			<b><p:message key="tutor.page.editTestForm" /></b>
		</div>
		<div class="card-body">
			<form action="/tutor/edit" method="POST">
				<label>
					<p:message key="tutor.page.name" />
				</label>
				<input type="text" class="form-control" name="test_name" value="${TEST.name}">
				<input value="${TEST.id}" name="test_id" hidden="true">
				<hr>
				<label>
					<p:message key="tutor.page.description" />
				</label>
				<br>
				<div style="width: 100%; margin-left: auto; margin-right: auto">
					<textarea name="test_description" class="form-control" style="width: 100%; height: 150px;">${TEST.description}</textarea>
				</div>
				<button type="submit" class="btn btn-success" style="margin: 10px">
					<p:message key="btn.save" />
				</button>
			</form>
			<hr>
			<form action="/tutor/edit/questions" method="GET">
				<input value="${TEST.id}" name="test_id" hidden="true">
				<button type="submit" class="btn btn-primary" style="margin: 10px">
					<p:message key="tutor.page.editQuestions" />
				</button>
			</form>
		</div>
	</div>
</div>