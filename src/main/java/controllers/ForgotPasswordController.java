package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ApplicationException;

@WebServlet("/forgot_password")
public class ForgotPasswordController extends AbstractController{

	private static final long serialVersionUID = -1776130290542769723L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(PAGE_TITLE, "page.name.forgotPassword");
		forwardToPage("main/forgot_1.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email =req.getParameter("email");
		try {
			getRegistrationService().forgotPassword(email);
			req.setAttribute(PAGE_TITLE, "page.name.forgotPassword");
			forwardToPage("main/forgot_2.jsp", req, resp);
		}catch(ApplicationException e){
			setError(req, e);
			req.setAttribute("email", email);
			req.setAttribute(PAGE_TITLE, "page.name.forgotPassword");
			forwardToPage("main/forgot_1.jsp", req, resp);
		}
	}
}
