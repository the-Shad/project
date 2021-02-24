package controllers.tutor.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tutor/edit/questions")
public class EditQuestionsController extends TutorEditController {
	private static final long serialVersionUID = -3591605414962579097L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("test_id"));
		req.getSession().setAttribute(TEST, getStudentService().getTestsbyId(id));
		req.setAttribute(PAGE_TITLE, "page.name.editQuestions");
		forwardToPage("tutor/editQuestions.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("question_id"));
		try {
			getTutorService().deleteQuestion(id);
			resetTest(req);
		}catch (Throwable e) {
			setError(req, e);
		}finally {
			req.setAttribute(PAGE_TITLE, "page.name.editQuestions");
			forwardToPage("tutor/editQuestions.jsp", req, resp);
		}
	}

}
