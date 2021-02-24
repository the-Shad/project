package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/confirm")
public class ConfirmRegistrationController extends AbstractController {
 
	private static final long serialVersionUID = -1567572559367492505L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = (String) req.getParameter("token");
		getRegistrationService().activation(token);
		resp.sendRedirect("/login");
	}
}
