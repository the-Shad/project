package controllers.tutor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import controllers.AbstractController;
import models.Test;

@WebServlet("/advtutor")
public class AdvancedTutorController extends AbstractController {

	private static final long serialVersionUID = -1776130290542769723L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = getCurrentPage(req);
		int maxPage = getTutorService().pagesTests();
		String search = req.getParameter("q");
		ArrayList<Test> list;
		if(StringUtils.isNotBlank(search)) {
			list = getStudentService().getTestsbySearch(search, page);
		}else {
			list = getTutorService().getAdvancedTestList(page);
		}
		setList(req, page, maxPage, list);
		setReqURI(req, req.getRequestURI());
		req.setAttribute(PAGE_TITLE, "page.name.advtutor");
		forwardToPage("tutor/tests.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getParameter("test_id");
		if(StringUtils.isNumeric(str)) {
			Long id = Long.parseLong(str);
			getTutorService().deleteTest(id);
		}
		resp.sendRedirect("/advtutor");
	}

}