package controllers.tutor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controllers.AbstractController;
import models.Result;
import models.User;

@WebServlet("/tutor/results")
public class TutorResultsController extends AbstractController {
	private static final long serialVersionUID = 1232261938330968248L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = ((User) req.getSession().getAttribute(CURRENT_USER)).getId();
		int page = getCurrentPage(req);
		int maxPage = getTutorService().pagesTutorResults(id);
		ArrayList<Result> list = getTutorService().getTutorResultList(id, page);
		setList(req, page, maxPage, list);
		setReqURI(req, "/tutor");
		req.setAttribute(PAGE_TITLE, "page.name.results");
		forwardToPage("tutor/tutResults.jsp", req, resp);
	}
}
