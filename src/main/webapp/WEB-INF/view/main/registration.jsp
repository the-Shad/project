<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>


<div class="container" style="width: 95%;">
	<a href="/home" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card">
		<div class="card-header">
			<p:message key="registration.page.form" />
		</div>
		<form action="/registration" method="POST">
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
						<input type="text" class="form-control" name="login" value="${FORM.login}" style="max-width: 500px;" placeholder="Enter your login">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.email" />
						</label>
						<input type="email" class="form-control" name="email" value="${FORM.email}" style="max-width: 500px;" placeholder="Enter your email">
						<small class="form-text text-muted"><p:message key="registration.page.emailMessage" /></small>
					</div>
				</div>
				<div class="row">
					<div class="form-group col">
						<label>
							<p:message key="registration.page.password" />
						</label>
						<input type="password" class="form-control" name="password" style="max-width: 500px;" placeholder="Enter your password">
					</div>
					<div class="form-group col">
						<label>
							<p:message key="registration.page.repeatPassword" />
						</label>
						<input type="password" class="form-control" name="repeatPassword" style="max-width: 500px;" placeholder="Repeat your password">
					</div>
				</div>
				<button type="submit" class="btn btn-success">
					<p:message key="registration.page.register" />
				</button>
			</div>

		</form>
	</div>
</div>