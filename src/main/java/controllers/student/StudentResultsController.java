package controllers.student;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controllers.AbstractController;
import models.User;

@WebServlet("/student/results")
public class StudentResultsController extends AbstractController {
	private static final long serialVersionUID = 6682271178017887178L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = getCurrentPage(req);
		User user = (User) req.getSession().getAttribute(CURRENT_USER);
		int maxPage = getStudentService().pagesStudentResults(user.getId());
		setList(req, page, maxPage, getStudentService().getResults(user.getId(), page));
		req.setAttribute(PAGE_TITLE, "page.name.results");
		forwardToPage("student/results.jsp", req, resp);
	}
}
