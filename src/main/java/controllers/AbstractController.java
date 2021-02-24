package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import context.ApplicationContext;
import models.forms.RegistrationForm;
import services.AdminService;
import services.CommonService;
import services.RegistrationService;
import services.StudentService;
import services.TutorService;

public class AbstractController extends HttpServlet {

	private static final long serialVersionUID = 1917114872885904660L;
	
	public static final String CURRENT_PAGE = "CURRENT_PAGE";
	public static final String MAX_PAGE = "MAX_PAGE";
	public static final String LIST = "LIST";
	public static final String PAGE_TITLE = "PAGE_TITLE";
	public static final String CURRENT_TEST = "CURRENT_TEST";
	public static final String CURRENT_QUESTION = "CURRENT_QUESTION";
	public static final String NEXT_QUESTION = "NEXT_QUESTION";
	public static final String RESULT_QUEUE = "RES_QUEUE";
	public static final String QESTION_QUEUE = "QES_QUEUE";
	public static final String CURRENT_USER = "CURRENT_USER";
	public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
	public static final String FORM = "FORM";
	public static final String REQUEST_URI = "requestURI";
	public static final String HOME = "/home";
	
	private ApplicationContext appCon;

	@Override
	public final void init() throws ServletException{
		appCon = ApplicationContext.getApplicationContext(getServletContext());
		initServlet();
	}
	@Override
	public void destroy() {
		appCon.shutDown();
	}
	
	protected void initServlet() throws ServletException{
	}
	

	protected void forwardToPage(String currentPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("currentPage", "/WEB-INF/view/" + currentPage);
		req.getRequestDispatcher("/WEB-INF/view/page-template.jsp").forward(req, resp);
	}
	
	public CommonService getCommonService() {
		return this.appCon.getCommonService();
	}
	public StudentService getStudentService() {
		return this.appCon.getStudentService();
	}
	public AdminService getAdminService() {
		return this.appCon.getAdminService();
	}
	public TutorService getTutorService() {
		return this.appCon.getTutorService();
	}
	public RegistrationService getRegistrationService(){
		return this.appCon.getRegistrationService();
	}
	public int getCurrentPage( HttpServletRequest req) {
		String pageString = (String) req.getParameter("page");
		int page;
		if (StringUtils.isBlank(pageString)) {
			page = 1;
		} else {
			page = Integer.parseInt(pageString);
		}
		return page;
	}
	public void setList(HttpServletRequest req, int page, int maxPage, Object list) {
		req.setAttribute(CURRENT_PAGE, page);
		req.setAttribute(MAX_PAGE, maxPage);
		req.setAttribute(LIST, list);
	}
	public void setError(HttpServletRequest req, Throwable e) {
		req.setAttribute(ERROR_MESSAGE, e.getMessage());
	}
	
	public RegistrationForm form(HttpServletRequest req) {
		RegistrationForm form = new RegistrationForm();
		form.setEmail(req.getParameter("email"));
		form.setFirstName(req.getParameter("firstName"));
		form.setLastName(req.getParameter("lastName"));
		form.setMiddleName(req.getParameter("middleName"));
		form.setLogin(req.getParameter("login"));
		form.setPassword(req.getParameter("password"));
		form.setRepeatPassword(req.getParameter("repeatPassword"));
		form.setActive(Boolean.parseBoolean(req.getParameter("active")));
		return form;
	}
	
	public void setTitle(HttpServletRequest req, String title) {
		req.setAttribute(PAGE_TITLE, title);
	}
	
	public void setReqURI(HttpServletRequest req, String uri) {
		req.setAttribute(REQUEST_URI, uri);
	}
}