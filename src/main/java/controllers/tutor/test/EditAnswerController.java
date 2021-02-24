package controllers.tutor.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ApplicationException;

@WebServlet("/tutor/edit/answer")
public class EditAnswerController extends TutorEditController {
	private static final long serialVersionUID = 3978333221230622478L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("answer_id"));
		req.setAttribute(ANSWER, getTutorService().getAnswer(id));
		req.setAttribute(PAGE_TITLE, "page.name.editAnswer");
		forwardToPage("tutor/editAnswer.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("answer_id"));
		String answer = req.getParameter("answer_answer");
		Boolean correct = Boolean.parseBoolean(req.getParameter("correct"));
		try {
			getTutorService().editAnswer(id, answer, correct);
			resetTest(req);
			resetQuestion(req);
			resetAnswer(req);
			resp.sendRedirect("/tutor/edit/answers?question_id=" + req.getParameter("question_id"));
		} catch (ApplicationException e) {
			setError(req, e);
			req.setAttribute(PAGE_TITLE, "page.name.editAnswer");
			forwardToPage("tutor/editAnswer.jsp", req, resp);
		}
	}

}
