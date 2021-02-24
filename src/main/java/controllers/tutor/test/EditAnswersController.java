package controllers.tutor.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ApplicationException;

@WebServlet("/tutor/edit/answers")
public class EditAnswersController extends TutorEditController {
	private static final long serialVersionUID = 6076129351033104533L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("question_id"));
		req.getSession().setAttribute(QUESTION, getTutorService().getQuestion(id));
		req.setAttribute(PAGE_TITLE, "page.name.editAnswers");
		forwardToPage("tutor/editAnswers.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		try {
			id = Long.parseLong(req.getParameter("answer_id"));	
		}catch (Throwable e) {
			resp.sendRedirect(HOME);
		}
		try {
			getTutorService().deleteAnswer(id);
			resetTest(req);
			resetQuestion(req);
		}catch (ApplicationException e) {
			setError(req, e);
		}finally {
			req.setAttribute(PAGE_TITLE, "page.name.editAnswers");
			forwardToPage("tutor/editAnswers.jsp", req, resp);
		}
	}
	
}
