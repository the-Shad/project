package controllers.tutor.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import models.Answer;

@WebServlet("/tutor/create/answer")
public class TutorCreateAnswerController extends AbstractController {
	private static final long serialVersionUID = -3740968150878447568L;
	
	private static final String ANSWER_ANSWER = "answer_answer";
	private static final String CORRECT = "correct";
	private static final String QUESTION_ID = "question_id";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setAttribute(PAGE_TITLE, "page.name.createAnswer");
		forwardToPage("tutor/createAnswer.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Answer answer = getAnswer(req);
		try {
			getTutorService().createAnswer(answer);
			resp.sendRedirect("/tutor/edit/answers?question_id="+req.getParameter(QUESTION_ID));
		} catch (Throwable e) {
			setError(req, e);
			req.setAttribute(CORRECT , answer);
			req.setAttribute(ANSWER_ANSWER, answer);
			req.setAttribute(PAGE_TITLE, "page.name.createAnswer");
			forwardToPage("tutor/createAnswer.jsp", req, resp);
		}
	}

	private Answer getAnswer(HttpServletRequest req) {
		Answer res = new Answer();
		res.setQuestionId(Long.parseLong(req.getParameter(QUESTION_ID)));
		res.setAnswer(req.getParameter(ANSWER_ANSWER));
		res.setCorrect(Boolean.parseBoolean(req.getParameter(CORRECT)));
		return res;
	}


}
