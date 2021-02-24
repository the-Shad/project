package controllers.student;

import java.io.IOException;
import java.util.ArrayDeque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.AbstractController;
import models.Answer;
import models.Question;
import models.Test;
import models.User;

@WebServlet("/student/testing")
public class StudentTestingController extends AbstractController {
	private static final long serialVersionUID = 5938993671750983899L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String backStr = (String) req.getParameter("back");
//		if (Boolean.parseBoolean(backStr)) {
////			Test test = (Test) session.getAttribute(CURRENT_TEST);	
////			Integer qesQueue = (Integer) session.getAttribute(QESTION_QUEUE);
//			if(qesQueue == 0) {
//				resp.sendRedirect("/student");
//			}else {
//				qesQueue -= 1;
//				((ArrayDeque<Float>) session.getAttribute(RESULT_QUEUE)).pollLast();
//				session.setAttribute(QESTION_QUEUE, qesQueue);
//				session.setAttribute(CURRENT_QUESTION, test.getCurrent(qesQueue));
//				session.setAttribute(NEXT_QUESTION, test.getNext(qesQueue));
//				req.setAttribute(PAGE_TITLE, test.getName());
//				forwardToPage("student/testingQuestion.jsp", req, resp);
//			}
//		} else {
//			resp.sendRedirect("/home");
//		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String[] vals = req.getParameterValues("answer");
		Test t = (Test) session.getAttribute(CURRENT_TEST);
		Question q = (Question) session.getAttribute(CURRENT_QUESTION);
		Integer queue = ((Integer) session.getAttribute(QESTION_QUEUE)) + 1;
		@SuppressWarnings("unchecked")
		ArrayDeque<Float> resQueue = (ArrayDeque<Float>) session.getAttribute(RESULT_QUEUE);
		addResult(vals, resQueue, q);
		Question qn = t.getCurrent(queue);
		req.setAttribute(PAGE_TITLE, t.getName());
		if (qn == null) {
			getStudentService().setResult((User) session.getAttribute(CURRENT_USER), t, resQueue);
			session.removeAttribute(CURRENT_TEST);
			session.removeAttribute(QESTION_QUEUE);
			session.removeAttribute(CURRENT_QUESTION);
			session.removeAttribute(NEXT_QUESTION);
			session.removeAttribute(RESULT_QUEUE);
			session.removeAttribute(QESTION_QUEUE);
			forwardToPage("student/result.jsp", req, resp);
		} else {
			session.setAttribute(QESTION_QUEUE, queue);
			session.setAttribute(CURRENT_QUESTION, qn);
			session.setAttribute(NEXT_QUESTION, t.getNext(queue));
			forwardToPage("student/testingQuestion.jsp", req, resp);
		}

	}
	
	private void addResult(String[] vals, ArrayDeque<Float> resQueue, Question q) {	
			try {
			int maxRes = q.correctAnswersNumber();
			int res = 0;
			for (String val : vals) {
				Answer a = q.getByAId(Long.parseLong(val));
				if (a.isCorrect()) {
					res++;
				}
			}
			resQueue.addLast(((float) res) / maxRes);
		}catch (NullPointerException e) {
			resQueue.addLast(0f);
		}
		
	}

}
