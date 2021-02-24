package controllers.tutor.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controllers.AbstractController;
import models.User;

@WebServlet("/tutor/create")
public class TutorCreateTestController extends AbstractController {
	private static final long serialVersionUID = 3844384549591346494L;

	private static final String NAME = "test_name";
	private static final String DESCRIPTON = "description";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(PAGE_TITLE, "page.name.createTest");
		forwardToPage("tutor/createTest.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String description = req.getParameter(DESCRIPTON);
		String name = req.getParameter(NAME);
		Long userId =((User) req.getSession().getAttribute(CURRENT_USER)).getId();
		try {
			getTutorService().createTest(userId, name, description);
			resp.sendRedirect("/tutor");
		} catch (Throwable e) {
			setError(req, e);
			req.setAttribute(NAME, name);
			req.setAttribute(DESCRIPTON, description);
			req.setAttribute(PAGE_TITLE, "page.name.createTest");
			forwardToPage("tutor/createTest.jsp", req, resp);
		}
	}

}
