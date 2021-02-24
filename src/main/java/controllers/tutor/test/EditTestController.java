package controllers.tutor.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tutor/edit")
public class EditTestController extends TutorEditController {
	private static final long serialVersionUID = -5549619798268219029L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("test_id"));
		req.getSession().setAttribute(TEST, getStudentService().getTestsbyId(id));
		req.setAttribute(PAGE_TITLE, "page.name.editTest");
		forwardToPage("tutor/editTest.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("test_id"));
		String name = req.getParameter("test_name");
		String description = req.getParameter("test_description");
		try {
			getTutorService().editTest(id, name, description);
			resetTest(req);
		}catch (Throwable e) {
			setError(req, e);
		}finally {
			req.setAttribute(PAGE_TITLE, "page.name.editTest");
			forwardToPage("tutor/editTest.jsp", req, resp);	
		}
	}

}
