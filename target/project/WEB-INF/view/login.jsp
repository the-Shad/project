<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<div class="container" style="max-width: 600px;">
	<a href="/home" class="btn btn-primary" style="margin-bottom: 10px; max-width: 100px;">Back</a>
	<div class="card">
		<div class="card-header">Login Form</div>
		<form action="/login" method="POST">
			<div class="card-body">
				<div class="form-group">
					<label>Login or Email</label> <input type="text" class="form-control" name="login"
						placeholder="Enter your login or email">
				</div>
				<div class="form-group">
					<label>Password</label> <input type="password" class="form-control" placeholder="Enter your password" name="login">
					<small id="emailHelp" class="form-text text-muted"><a href="/forgot_password">Forgot Password?</a></small>
				</div>
				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input"> <label class="form-check-label" for="exampleCheck1">Remember me</label>
				</div>
				<button type="submit" class="btn btn-primary">Log In</button>
			</div>

		</form>
	</div>
</div>