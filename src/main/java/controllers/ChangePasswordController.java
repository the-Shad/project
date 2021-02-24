package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import exceptions.ApplicationException;

@WebServlet("/change_password")
public class ChangePasswordController extends AbstractController {
	private static final long serialVersionUID = 2546564329650869424L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getParameter("token");
		if (StringUtils.isBlank(token)) {
			resp.sendRedirect("/home");
		} else {
			req.setAttribute("TOKEN", token);
			req.setAttribute(PAGE_TITLE, "page.name.changePassword");
			forwardToPage("main/changePassword.jsp", req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getParameter("token");
		String password = req.getParameter("password");
		String repeatPassword = req.getParameter("repeatPassword");
		try {
			getRegistrationService().changePassword(token, password, repeatPassword);
			resp.sendRedirect("/login");
		} catch (ApplicationException e) {
			req.setAttribute(PAGE_TITLE, "page.name.changePassword");
			forwardToPage("main/changePassword.jsp", req, resp);
		}
	}

}
