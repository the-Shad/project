package controllers.tutor.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import models.Question;

@WebServlet("/tutor/create/question")
public class TutorCreateQuestionController extends AbstractController {
	private static final long serialVersionUID = 3721639255699296090L;
	
	private static final String QUESTION_QUESTION = "question_question";
	private static final String MULTI_RESPONSE = "multi_response";
	private static final String TEST_ID = "test_id";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setAttribute(PAGE_TITLE, "page.name.createQuestion");
		forwardToPage("tutor/createQuestion.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Question question = getQuestion(req);
		try {
			getTutorService().createQuestion(question);
			resp.sendRedirect("/tutor/edit/questions?test_id="+req.getParameter(TEST_ID));
		} catch (Throwable e) {
			setError(req, e);
			req.setAttribute(QUESTION_QUESTION, question.getQuestion());
			req.setAttribute(MULTI_RESPONSE, question.isMultiResponse());
			req.setAttribute(TEST_ID, question.getTestId());
			req.setAttribute(PAGE_TITLE, "page.name.createQuestion");
			forwardToPage("tutor/createQuestion.jsp", req, resp);
		}
	}

	private Question getQuestion(HttpServletRequest req) {
		Question res = new Question();
		res.setTestId(Long.parseLong(req.getParameter(TEST_ID)));
		res.setQuestion(req.getParameter(QUESTION_QUESTION));
		res.setMultiResponse(Boolean.parseBoolean(req.getParameter(MULTI_RESPONSE)));
		return res;
	}
}
