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
			<form action="/tutor/edit/answer" method="POST">
				<input value="${ANSWER.id}" name="answer_id" hidden="true">
				<input value="${ANSWER.questionId}" name="question_id" hidden="true">
				<label>
					<p:message key="tutor.page.answer" />
				</label>
				<br>
				<input type="text" value="${ANSWER.answer}" name="answer_answer" class="form-control" style="width: 100%;">
				<div class="form-check" style="margin: 10px; margin-left: 15px">
					<c:if test="${not ANSWER.correct}">
						<input class="form-check-input" type="checkbox" name="correct" value="true">
					</c:if>
					<c:if test="${ANSWER.correct}">
						<input class="form-check-input" type="checkbox" value="true" name="correct" checked="checked">
					</c:if>
					<label class="form-check-label">
						<p:message key="tutor.page.correct" />
					</label>
				</div>
				<button type="submit" class="btn btn-success" style="margin: 10px">
					<p:message key="btn.save" />
				</button>
			</form>
		</div>
	</div>
</div>