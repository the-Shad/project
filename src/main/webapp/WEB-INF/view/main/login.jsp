<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="max-width: 600px;">
	<a href="/home" class="btn btn-primary" style="margin-bottom: 10px;"><p:message key="btn.back"/></a>
	<div class="card">
		<div class="card-header"><p:message key="login.page.form"/></div>
		<form action="/login" method="POST">
			<div class="card-body">
				<div class="form-group">
					<label><p:message key="login.page.loginOrEmail"/></label> <input type="text" class="form-control" name="login"
						placeholder="Enter your login or email" value="${login}">
				</div>
				<div class="form-group">
					<label><p:message key="login.page.password"/></label> <input type="password" class="form-control" placeholder="Enter your password" name="password">
					<small class="form-text text-muted"><a href="/forgot_password"><p:message key="login.page.forgot"/></a></small>
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" name="remember"> <label class="form-check-label"><p:message key="login.page.remember"/></label>
				</div>
				<button type="submit" class="btn btn-primary"><p:message key="login.page.logIn"/></button>
			</div>

		</form>
	</div>
</div>