<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="width: 95%;">
	<form action="/tutor/edit/questions" method="GET">
		<input value="${TEST.id}" name="test_id" hidden="true">
		<button type="submit" class="btn btn-primary" style="margin-bottom: 10px;">
			<p:message key="btn.back" />
		</button>
	</form>
	<div class="card" style="margin: 10px">
		<div class="card-header">
			<b>${TEST.name}</b>
		</div>
		<div class="card-body">
			<form action="/tutor/create/question" method="POST">
				<label>
					<p:message key="tutor.page.question" />
				</label>
				<br>
				<input type="text" value="${question_question}" name="question_question" style="width: 70%">
				<br>
				<input value="${TEST.id}" name="test_id" hidden="true">
				<div class="form-check" style="margin:10px">
					<c:if test="${multi_response}">
						<input class="form-check-input" type="checkbox" value="true" name="multi_response" checked="checked">
					</c:if>
					<c:if test="${not multi_response}">
						<input class="form-check-input" type="checkbox" value="true" name="multi_response">
					</c:if>
					<label class="form-check-label">
						<p:message key="tutor.page.multiResponse" />
					</label>
				</div>
				<button type="submit" class="btn btn-success">
					<p:message key="btn.save" />
				</button>
			</form>
		</div>
	</div>
</div>