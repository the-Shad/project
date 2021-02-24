<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="width: 95%;">
	<form action="/tutor/edit/answers" method="GET">
		<input value="${QUESTION.id}" name="question_id" hidden="true">
		<button type="submit" class="btn btn-primary" style="margin-bottom: 10px;">
			<p:message key="btn.back" />
		</button>
	</form>
	<div class="card" style="margin: 10px">
		<div class="card-header">
			<b>${TEST.name}</b>
			<hr />
			${QUESTION.question}
		</div>
		<div class="card-body">
			<form action="/tutor/create/answer" method="POST">
				<input value="${QUESTION.id}" name="question_id" hidden="true">
				<label>
					<p:message key="tutor.page.answer" />
				</label>
				<br>
				<input type="text" value="${answer_answer}" class="form-control"  name="answer_answer" style="width: 100%">
				<div class="form-check" style="margin: 10px; margin-left: 15px">
					<c:if test="${correct}">
						<input class="form-check-input" type="checkbox" name="correct" value="true" checked="checked">
					</c:if>
					<c:if test="${not correct}">
						<input class="form-check-input" type="checkbox" name="correct" value="true">
					</c:if>
					<label class="form-check-label">
						<p:message key="tutor.page.correct" />
					</label>
				</div>
				<button type="submit" class="btn btn-success">
					<p:message key="btn.save" />
				</button>
			</form>
		</div>
	</div>
</div>