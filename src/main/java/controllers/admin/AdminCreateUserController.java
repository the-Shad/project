package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import exceptions.ApplicationException;
import models.forms.RegistrationForm;

@WebServlet("/admin/create")
public class AdminCreateUserController extends AbstractController {
	private static final long serialVersionUID = 26377835522614041L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(PAGE_TITLE, "page.name.adminCreateUser");
		forwardToPage("admin/createUser.jsp", req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegistrationForm form = form(req);
		try {
			getRegistrationService().registration(form);
			resp.sendRedirect("/admin");
		} catch (ApplicationException e) {
			setError(req, e);
			req.setAttribute(FORM, form);
			req.setAttribute(PAGE_TITLE, "page.name.adminCreateUser");
			forwardToPage("admin/createUser.jsp", req, resp);
		}
	}

}
