<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="width: 95%;">
	<a href="/admin" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card">
		<div class="card-header">
			<p:message key="admin.page.editUserForm" />
		</div>
		<form action="/admin/edit" method="POST">
			<div class="card-body">
				<input name="user_id" value="${FORM.id}" hidden="true">
				<div class="row">
					<div class="form-group col">
						<label>
							<p:message key="registration.page.last" />
						</label>
						<input type="text" class="form-control" name="lastName" value="${FORM.lastName}">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.first" />
						</label>
						<input type="text" class="form-control" name="firstName" value="${FORM.firstName}">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.middle" />
						</label>
						<input type="text" class="form-control" name="middleName" value="${FORM.middleName}">
					</div>
				</div>

				<div class="row">
					<div class="form-group col">
						<label>
							<p:message key="registration.page.login" />
						</label>
						<input type="text" class="form-control" name="login" value="${FORM.login}">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.email" />
						</label>
						<input type="email" class="form-control" name="email" value="${FORM.email}">
					</div>
				</div>
				<div style="margin: 10px; margin-left: 40px">
					<c:if test="${FORM.active}">
						<input class="form-check-input" type="checkbox" name="active" value="true" checked>
					</c:if>
					<c:if test="${not FORM.active}">
						<input class="form-check-input" type="checkbox" name="active" value="true">
					</c:if>
					<label class="form-check-label">
						<p:message key="admin.page.active" />
					</label>
				</div>
				<hr>
				<div class="row" style="margin-left: 20px">
					<div class="form-check col">
						<c:if test="${FORM.admin}">
							<input class="form-check-input" type="checkbox" name="role" value="0" checked>
						</c:if>
						<c:if test="${not FORM.admin}">
							<input class="form-check-input" type="checkbox" name="role" value="0">
						</c:if>
						<label class="form-check-label">
							<p:message key="admin.page.admin" />
						</label>
					</div>
					<div class="form-check col">
						<c:if test="${FORM.advanced}">
							<input class="form-check-input" type="checkbox" name="role" value="1" checked>
						</c:if>
						<c:if test="${not FORM.advanced}">
							<input class="form-check-input" type="checkbox" name="role" value="1">
						</c:if>
						<label class="form-check-label">
							<p:message key="admin.page.advtutor" />
						</label>
					</div>
					<div class="form-check col">
						<c:if test="${FORM.tutor}">
							<input class="form-check-input" type="checkbox" name="role" value="2" checked>
						</c:if>
						<c:if test="${not FORM.tutor}">
							<input class="form-check-input" type="checkbox" name="role" value="2">
						</c:if>
						<label class="form-check-label">
							<p:message key="admin.page.tutor" />
						</label>
					</div>
					<div class="form-check col">
						<c:if test="${FORM.student}">
							<input class="form-check-input" type="checkbox" name="role" value="3" checked>
						</c:if>
						<c:if test="${not FORM.student}">
							<input class="form-check-input" type="checkbox" name="role" value="3">
						</c:if>
						<label class="form-check-label">
							<p:message key="admin.page.student" />
						</label>
					</div>
				</div>
				<hr>
				<button type="submit" class="btn btn-success" style="margin: 10px">
					<p:message key="btn.save" />
				</button>
			</div>

		</form>
	</div>
</div>