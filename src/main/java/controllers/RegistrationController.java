package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ApplicationException;
import models.forms.RegistrationForm;

@WebServlet("/registration")
public class RegistrationController extends AbstractController {
	private static final long serialVersionUID = 4110225756639668472L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setTitle(req, "page.name.registration");
		forwardToPage("main/registration.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegistrationForm form = form(req);
		try {
			getRegistrationService().registration(form);
			resp.sendRedirect("/home");
		} catch (ApplicationException e) {
			setError(req, e);
			req.setAttribute(FORM, form);
			setTitle(req, "page.name.registration");
			forwardToPage("main/registration.jsp", req, resp);
		}

	}

	

}