<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<div class="container" style="width: 95%;">
	<a href="/home" class="btn btn-primary" style="margin-bottom: 10px; max-width: 100px;">Back</a>
	<div class="card">
		<div class="card-header">Registration Form</div>
		<form action="/registration" method="POST">
			<div class="card-body">
				<div class="row">
					<div class="form-group col">
						<label>First Name</label> <input type="text" class="form-control" name="firstName" style="max-width: 500px;">
					</div>
					<div class="form-group col">
						<label>Middle Name</label> <input type="text" class="form-control" name="middleName" style="max-width: 500px;">
					</div>
					<div class="form-group col">
						<label>Last Name</label> <input type="text" class="form-control" name="lastName" style="max-width: 500px;">
					</div>
				</div>
				<div class="row">
					<div class="form-group col">
						<label>Login</label> <input type="text" class="form-control" name="login" style="max-width: 500px;" placeholder="Enter your login">
					</div>
					<div class="form-group col">
						<label>Email</label> <input type="email" class="form-control" name="email" style="max-width: 500px;" placeholder="Enter your email"> <small
							class="form-text text-muted">We'll never share your email with anyone else.</small>
					</div>
				</div>
				<div class="row">
					<div class="form-group col">
						<label>Password</label> <input type="password" class="form-control" name="password" style="max-width: 500px;" placeholder="Enter your password">
					</div>
					<div class="form-group col">
						<label>Repeat password</label> <input type="password" class="form-control" name="repeatPassword" style="max-width: 500px;"
							placeholder="Repeat your password">
					</div>
				</div>
				<button type="submit" class="btn btn-success" style="max-width: 100px;">Register</button>
			</div>

		</form>
	</div>
</div>