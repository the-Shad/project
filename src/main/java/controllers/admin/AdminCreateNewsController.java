package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import exceptions.ApplicationException;
import models.News;

@WebServlet("/admin/news/create")
public class AdminCreateNewsController extends AbstractController {
	private static final long serialVersionUID = 2648538212985151106L;
	private static final String NAME = "name";
	private static final String NEWS ="news";
	private static final String NEWS_TEXT ="news_text";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(PAGE_TITLE, "page.name.adminCreateNews");
		forwardToPage("admin/newsCreate.jsp", req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		News news = news(req);
		try {
			getAdminService().createNews(news);;
			resp.sendRedirect("/home");
		} catch (ApplicationException e) {
			setError(req, e);
			req.setAttribute(NEWS, news);
			req.setAttribute(PAGE_TITLE, "page.name.adminCreateNews");
			forwardToPage("admin/newsCreate.jsp", req, resp);
		}
	}
	
	private News news(HttpServletRequest req) {
		News news = new News();
		news.setName(req.getParameter(NAME));
		news.setNews(req.getParameter(NEWS_TEXT));
		return news;
	}

}
