package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import exceptions.ApplicationException;
import models.News;

@WebServlet("/admin/news/edit")
public class AdminEditNewsController extends AbstractController {
	private static final long serialVersionUID = -8702483279302953325L;

	private static final String NAME = "name";
	private static final String NEWS ="news";
	private static final String NEWS_TEXT ="news_text";
	private static final String ID = "news_id";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("news_id"));
		req.setAttribute(NEWS, getAdminService().getNewsById(id));
		req.setAttribute(PAGE_TITLE, "page.name.adminEditNews");
		forwardToPage("admin/newsEdit.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		News news = news(req);
		try {
			getAdminService().editNews(news);
			resp.sendRedirect("/home");
		}catch(ApplicationException e) {
			setError(req, e);
			req.setAttribute(NEWS, news);
			req.setAttribute(PAGE_TITLE, "page.name.adminEditNews");
			forwardToPage("admin/newsEdit.jsp", req, resp);
		}
	}
	
	private News news(HttpServletRequest req) {
		News news = new News();
		news.setId(Long.parseLong(req.getParameter(ID)));
		news.setName(req.getParameter(NAME));
		news.setNews(req.getParameter(NEWS_TEXT));
		return news;
	}
}
