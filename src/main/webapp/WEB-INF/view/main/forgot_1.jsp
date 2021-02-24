<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="max-width: 600px;">
	<a href="/login" class="btn btn-primary" style="margin-bottom: 10px;">
		<p:message key="btn.back" />
	</a>
	<div class="card">
		<div class="card-header">
			<p:message key="forgot.page.form" />
		</div>
		<form action="/forgot_password" method="POST">
			<div class="card-body">
				<div class="form-group">
					<label>
						<p:message key="forgot.page.email" />
					</label>
					<input type="email" class="form-control" name="email" placeholder="Enter your email" value="${email}">
					<p style="margin: 10px;">
						<p:message key="forgot.page.emailMessage" />
					</p>
				</div>
				<button type="submit" class="btn btn-primary">
					<p:message key="forgot.page.send" />
				</button>
			</div>

		</form>
	</div>
</div>