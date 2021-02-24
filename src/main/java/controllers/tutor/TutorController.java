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
import models.User;

@WebServlet("/tutor")
public class TutorController extends AbstractController{

	private static final long serialVersionUID = -7055916793118564220L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = ((User) req.getSession().getAttribute(CURRENT_USER)).getId();
		int page = getCurrentPage(req);
		int maxPage = getTutorService().pagesTutorTests(id);
		String search = req.getParameter("q");
		ArrayList<Test> list;
		if(StringUtils.isNotBlank(search)) {
			list = getTutorService().searchTutor(search, page, id);
		}else {
			list = getTutorService().getTutorTestList(id, page);
		}
		setList(req, page, maxPage, list);
		setReqURI(req, req.getRequestURI());
		req.setAttribute(PAGE_TITLE, "page.name.tutor");
		forwardToPage("tutor/tests.jsp", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getParameter("test_id");
		if(StringUtils.isNumeric(str)) {
			Long id = Long.parseLong(str);
			getTutorService().deleteTest(id);
		}
		resp.sendRedirect("/tutor");
	}
	
}
