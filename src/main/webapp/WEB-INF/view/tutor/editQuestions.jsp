<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="width: 95%;">
	<form action="/tutor/edit" method="GET">
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
			<c:forEach var="question" items="${TEST.questions}">
				<div class="row">
					<div class="col">${question.question}</div>
					<div class="col" style="max-width: 15%">
						<form action="/tutor/edit/question" method="GET">
							<input value="${question.id}" name="question_id" hidden="true">
							<button type="submit" class="btn btn-success" style="margin: 10px">
								<p:message key="btn.edit" />
							</button>
						</form>
					</div>
					<div class="col" style="max-width: 15%">
						<form action="/tutor/edit/questions" method="POST">
							<input value="${question.id}" name="question_id" hidden="true">
							<button type="submit" class="btn btn-danger" style="margin: 10px">
								<p:message key="btn.delete" />
							</button>
						</form>
					</div>
				</div>
				<hr>
			</c:forEach>
			<form action="/tutor/create/question" method="GET">
				<input value="${TEST.id}" name="test_id" hidden="true">
				<button type="submit" class="btn btn-primary" style="margin: 10px">
					<p:message key="tutor.page.createQuestion" />
				</button>
			</form>
		</div>
	</div>
</div>