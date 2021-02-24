package controllers.student;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import controllers.AbstractController;
import models.Test;

@WebServlet("/student")
public class StudentController extends AbstractController {
	private static final long serialVersionUID = 3338770484633292903L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = getCurrentPage(req);
		int maxPage = getTutorService().pagesTests();
		String search = req.getParameter("q");
		ArrayList<Test> list;
		if (StringUtils.isNotBlank(search)) {
			list = getStudentService().getTestsbySearch(search, maxPage);
		} else {
			list = getTutorService().getAdvancedTestList(page);
		}
		setList(req, page, maxPage, list);
		req.setAttribute(PAGE_TITLE, "page.name.student");
		forwardToPage("student/student.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String testIdStr = (String) req.getParameter("test_id");
		if (StringUtils.isNotBlank(testIdStr)) {
			Long testId= Long.parseLong(testIdStr);
			Integer qesQueue = 0;
			Test test = getStudentService().getTestsbyId(testId);
			HttpSession session = req.getSession();
			session.setAttribute(CURRENT_TEST, test);
			session.setAttribute(RESULT_QUEUE, new ArrayDeque<Float>());
			session.setAttribute(QESTION_QUEUE, qesQueue);
			session.setAttribute(CURRENT_QUESTION, test.getCurrent(qesQueue));
			session.setAttribute(NEXT_QUESTION, test.getNext(qesQueue));
			req.setAttribute(PAGE_TITLE, test.getName());
			forwardToPage("student/testingQuestion.jsp", req, resp);
		}
	}
}
