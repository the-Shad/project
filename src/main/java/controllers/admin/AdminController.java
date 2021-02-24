package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import controllers.AbstractController;
import models.User;

@WebServlet("/admin")
public class AdminController extends AbstractController {

	private static final long serialVersionUID = 8203937079800137744L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = getCurrentPage(req);
		int maxPage = getAdminService().pages();
		String search = req.getParameter("q");
		ArrayList<User> list;
		if (StringUtils.isNotBlank(search)) {
			list = getAdminService().search(search, page);
		} else {
			list = getAdminService().getUserListPage(page);
		}
		setList(req, page, maxPage, list);
		req.setAttribute(PAGE_TITLE, "page.name.admin");
		forwardToPage("admin/admin.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("user_id");
		if (StringUtils.isNotBlank(sid)) {
			Long id = Long.parseLong(sid);
			getAdminService().delete(id);
		}
		resp.sendRedirect("/admin");
	}

}
