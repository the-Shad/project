package controllers.tutor.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tutor/edit/question")
public class EditQuestionController extends TutorEditController {
	private static final long serialVersionUID = 8452500055836835590L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("question_id"));
		req.getSession().setAttribute(QUESTION, getTutorService().getQuestion(id));
		req.setAttribute(PAGE_TITLE, "page.name.editQuestion");
		forwardToPage("tutor/editQuestion.jsp", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("question_id"));
		String question = req.getParameter("question_question");
		Boolean multiResponse = Boolean.parseBoolean(req.getParameter("multi_response"));
		try {
			getTutorService().editQuestion(id, question, multiResponse);
			resetTest(req);
			resetQuestion(req);
			resp.sendRedirect("/tutor/edit/questions?test_id="+req.getParameter("test_id"));
		}catch (Throwable e) {
			setError(req, e);
			req.setAttribute(PAGE_TITLE, "page.name.editQuestion");
			forwardToPage("tutor/editQuestion.jsp", req, resp);
		}
	}
}
