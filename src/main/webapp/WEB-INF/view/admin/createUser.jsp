<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>


<div class="container" style="width: 95%;">
	<a href="/admin" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card">
		<div class="card-header">
			<p:message key="admin.page.createUserForm" />
		</div>
		<form action="/admin/create" method="POST">
			<div class="card-body">
				<div class="row">
					<div class="form-group col">
						<label>
							<p:message key="registration.page.last" />
						</label>
						<input type="text" class="form-control" name="lastName" value="${FORM.lastName}" style="max-width: 500px;">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.first" />
						</label>
						<input type="text" class="form-control" name="firstName" value="${FORM.firstName}" style="max-width: 500px;">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.middle" />
						</label>
						<input type="text" class="form-control" name="middleName" value="${FORM.middleName}" style="max-width: 500px;">
					</div>
				</div>
				<div class="row">
					<div class="form-group col">
						<label>
							<p:message key="registration.page.login" />
						</label>
						<input type="text" class="form-control" name="login" value="${FORM.login}" style="max-width: 500px;" placeholder="Enter login">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.email" />
						</label>
						<input type="text" class="form-control" name="email" value="${FORM.email}" style="max-width: 500px;" placeholder="Enter email">
					</div>
				</div>
				<div class="row">
					<div class="form-group col">
						<label>
							<p:message key="registration.page.password" />
						</label>
						<input type="password" class="form-control" name="password" style="max-width: 500px;" placeholder="Enter password">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.repeatPassword" />
						</label>
						<input type="password" class="form-control" name="repeatPassword" style="max-width: 500px;" placeholder="Repeat password">
					</div>
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="active" value="true">
					<label class="form-check-label">
						<p:message key="admin.page.active" />
					</label>
				</div>
				<button type="submit" class="btn btn-success">
					<p:message key="btn.save" />
				</button>
			</div>
		</form>
	</div>
</div>