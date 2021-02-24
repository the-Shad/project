package controllers;

import java.io.IOException;
import java.util.ArrayList;
import static controllers.LoginController.IS_ADMIN;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.News;

@WebServlet(urlPatterns = { "/home", "/index.html"})
public class HomeController extends AbstractController {

	private static final long serialVersionUID = -967543839401141809L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = getCurrentPage(req);
		int maxPage = getAdminService().pagesHome();
		ArrayList<News> list = getAdminService().newsList(page);
		setList(req, page, maxPage, list);
		req.setAttribute(PAGE_TITLE, "page.name.home");
		forwardToPage("main/home.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean adm = (Boolean) req.getSession().getAttribute(IS_ADMIN);
		if (adm) {
			Long id = Long.parseLong(req.getParameter("news_id"));
			getAdminService().deleteNews(id);	
		}
		resp.sendRedirect("/home");
	}

}
