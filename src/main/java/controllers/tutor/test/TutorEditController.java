package controllers.tutor.test;


import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import models.Answer;
import models.Question;
import models.Test;

public abstract class TutorEditController extends AbstractController {
	private static final long serialVersionUID = 8120649626850756328L;
	
	public static final String TEST = "TEST";
	public static final String QUESTION = "QUESTION";
	public static final String ANSWER = "ANSWER";
	

	public void resetTest(HttpServletRequest req) {
		Long id = ((Test) req.getSession().getAttribute(TEST)).getId();
		req.getSession().setAttribute(TEST, getStudentService().getTestsbyId(id));
	}
	public void resetQuestion(HttpServletRequest req) {
		Long id = ((Question) req.getSession().getAttribute(QUESTION)).getId();
		req.getSession().setAttribute(QUESTION, getStudentService().getTestsbyId(id));
	}
	public void resetAnswer(HttpServletRequest req) {
		Long id = ((Answer) req.getSession().getAttribute(ANSWER)).getId();
		req.getSession().setAttribute(ANSWER, getStudentService().getTestsbyId(id));
	}
}
