<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="width: 95%;">
	<form action="/tutor/edit/question" method="GET">
		<input value="${QUESTION.id}" name="question_id" hidden="true">
		<button type="submit" class="btn btn-primary">
			<p:message key="btn.back" />
		</button>
	</form>
	<div class="card" style="margin: 10px">
		<div class="card-header">
			<b>${TEST.name}</b>
			<hr />
			<div class="row">
				<div class="col">${QUESTION.question}</div>
				<div class="col">
					<c:if test="${QUESTION.multiResponse}">
						<input class="form-check-input" type="checkbox" checked disabled="disabled">
					</c:if>
					<c:if test="${not QUESTION.multiResponse}">
						<input class="form-check-input" type="checkbox" disabled="disabled">
					</c:if>
					<label class="form-check-label">
						<p:message key="tutor.page.multiResponse" />
					</label>
				</div>
			</div>
		</div>
		<div class="card-body">
			<c:forEach var="answer" items="${QUESTION.answers}">
				<div class="row">
					<div class="col" style="margin-left: 15px;">
						<c:if test="${answer.correct}">
							<input class="form-check-input" type="radio" checked disabled="disabled">
						</c:if>
						<c:if test="${not answer.correct}">
							<input class="form-check-input" type="radio" disabled="disabled">
						</c:if>
						${answer.answer}
					</div>
					<div class="col" style="max-width: 15%">
						<form action="/tutor/edit/answer" method="GET">
							<input value="${answer.id}" name="answer_id" hidden="true">
							<button type="submit" class="btn btn-success" style="margin: 10px">
								<p:message key="btn.edit" />
							</button>
						</form>
					</div>
					<div class="col" style="max-width: 15%">
						<form action="/tutor/edit/answers" method="POST">
							<input value="${answer.id}" name="answer_id" hidden="true">
							<button type="submit" class="btn btn-danger" style="margin: 10px">
								<p:message key="btn.delete" />
							</button>
						</form>
					</div>
				</div>
				<hr>
			</c:forEach>
			<form action="/tutor/create/answer" method="GET">
				<input value="${QUESTION.id}" name="question_id" hidden="true">
				${question.question}
				<button type="submit" class="btn btn-primary" style="margin: 10px">
					<p:message key="tutor.page.createAnswer" />
				</button>
			</form>
		</div>
	</div>
</div>