package controllers.tutor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import models.Result;

@WebServlet("/advtutor/results")
public class AdvancedResultsController extends AbstractController {
	private static final long serialVersionUID = -7531220999202310582L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = getCurrentPage(req);
		int maxPage = getTutorService().pagesResults();
		ArrayList<Result> list = getTutorService().getAdvancedResultList(page);
		setList(req, page, maxPage, list);
		setReqURI(req, "/advtutor");
		req.setAttribute(PAGE_TITLE, "page.name.advresults");
		forwardToPage("tutor/tutResults.jsp", req, resp);
	}

}
