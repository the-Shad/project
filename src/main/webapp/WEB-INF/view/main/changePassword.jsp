<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container">
	<a href="/home" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card">
		<div class="card-header">
			<p:message key="login.page.form" />
		</div>
		<form action="/change_password" method="POST">
			<div class="card-body">
				<div class="form-group">
				<input type="text" value="${TOKEN}" name="token" hidden="true">
					<label>
						<p:message key="change.page.password" />
					</label>
					<input type="password" class="form-control" placeholder="Enter your password" name="password">
				</div>
				<div class="form-group">
					<label>
						<p:message key="change.page.repeatPassword" />
					</label>
					<input type="password" class="form-control" placeholder="Repeat your password" name="repeatPassword">
				</div>
				<button type="submit" class="btn btn-primary">
					<p:message key="change.page.change" />
				</button>
			</div>

		</form>
	</div>
</div>