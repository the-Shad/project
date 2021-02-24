package controllers;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.ApplicationException;
import models.User;
import services.CommonService;

@WebServlet("/login")
public class LoginController extends AbstractController{

	private static final long serialVersionUID = 7032762537889822394L;
	

	public static final String IS_ADMIN = "IS_ADMIN";
	public static final String IS_ADVTUTOR = "IS_ADVTUTOR";
	public static final String IS_TUTOR = "IS_TUTOR";
	public static final String IS_STUDENT = "IS_STUDENT";

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setTitle(req, "page.name.login");
		forwardToPage("main/login.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		try {
			User user = getCommonService().login(login, password);
			setSessionRoles(req, user);
			req.getSession().setAttribute(CURRENT_USER, user);
			if(Boolean.parseBoolean(req.getParameter("remember"))) {
				req.getSession().setMaxInactiveInterval(60*60*60*24*30);
			}
			resp.sendRedirect("/home");
		}catch (ApplicationException e) {
			setError(req, e);
			setTitle(req, "page.name.login");
			req.setAttribute("login", login);
			forwardToPage("main/login.jsp", req, resp);
		}
	}
	
	private void setSessionRoles(HttpServletRequest req, User user) {
		HttpSession sess = req.getSession();
		CommonService cs = getCommonService();
		if (cs.isAdmin(user)) {
			sess.setAttribute(IS_ADMIN, true);
		}
		if (cs.isAdvTutor(user)) {
			sess.setAttribute(IS_ADVTUTOR, true);
		}
		if (cs.isTutor(user)) {
			sess.setAttribute(IS_TUTOR, true);
		}
		if (cs.isStudent(user)) {
			sess.setAttribute(IS_STUDENT, true);
		}
	}
	
}
