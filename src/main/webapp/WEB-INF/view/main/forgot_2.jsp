<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/project.tld" prefix="p"%>

<div class="container" style="max-width: 600px;">

	<div class="card">
		<div class="card-header">
			<p:message key="forgot.page.form" />
		</div>
		<p style="margin: 10px;">
			<p:message key="forgot.page.sentMessage" />
		</p>
		<a href="/login" class="btn btn-primary" style="margin-bottom: 10px;">
			<p:message key="btn.back" />
		</a>
	</div>
</div>