<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div>
	<form action="/student/testing" method="GET">
		<input name="back" value="true" hidden="true">
		<button type="submit" class="btn btn-primary" style="margin-bottom: 10px;">
			<p:message key="btn.back" />
		</button>
	</form>


	<form action="/student/testing" method="POST">
		<div class="card">
			<div class="card-header">
				<label>${CURRENT_QUESTION.question}</label>
			</div>
			<div class="card-body">
			
			
				<c:if test="${not CURRENT_QUESTION.multiResponse}">
					<c:forEach var="an" items="${CURRENT_QUESTION.answers}">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="answer" value="${an.id}">
							<label class="form-check-label">${an.answer} </label>
						</div>
					</c:forEach>
				</c:if>
				
				
				<c:if test="${CURRENT_QUESTION.multiResponse}">
					<c:forEach var="an" items="${CURRENT_QUESTION.answers}">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="${an.id}" name="answer">
							<label class="form-check-label"> ${an.answer}</label>
						</div>
					</c:forEach>
				</c:if>
			</div>

			<input type="number" name="test_id" value="${CURRENT_TEST.id}" hidden="true">
			<input type="number" name="question_id" value="${CURRENT_QUESTION.id}" hidden="true">
			<div>
				<c:if test="${NEXT_QUESTION != null}">
					<button type="submit" class="btn btn-primary" style="margin:10px">
						<p:message key="testing.page.next" />
					</button>
				</c:if>
				<c:if test="${NEXT_QUESTION == null}">
					<button type="submit" class="btn btn-primary" style="margin:10px">
						<p:message key="testing.page.finish" />
					</button>
				</c:if>
			</div>
		</div>
	</form>
</div>