package filters;

import static controllers.AbstractController.CURRENT_USER;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import context.ApplicationContext;
import models.User;
import services.CommonService;


@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter extends AbstractFilter {

	public static final String[] PROT_URI = {"/admin", "/tutor", "/advtutor", "/student" };
	public static final String ADMIN_URI = "/admin";
	public static final String ADVTUTOR_URI = "/advtutor";
	public static final String TUTOR_URI = "/tutor";
	public static final String STUDENT_URI = "/student";
	
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			User user = (User) req.getSession().getAttribute(CURRENT_USER);
		if (access(req, user, req.getRequestURI())) {
			chain.doFilter(req, resp);
		} else {
			if (user == null) {
				resp.sendRedirect("/login");
			} else {
				resp.sendRedirect("/home");
			}
		}
		}catch (Throwable e) {
			req.getSession().invalidate();
			resp.sendRedirect("/home");
		}
		
	}
	
	public boolean access(HttpServletRequest req, User user, String requestURI) {
		CommonService cs = ApplicationContext.getApplicationContext(req.getServletContext()).getCommonService();
		if (((cs.isAdmin(user) && requestURI.startsWith(ADMIN_URI))
				|| (cs.isAdvTutor(user) && requestURI.startsWith(ADVTUTOR_URI))
				|| (cs.isTutor(user) && requestURI.startsWith(TUTOR_URI))
				|| (cs.isStudent(user) && requestURI.startsWith(STUDENT_URI))
				|| (!isProtected( requestURI)))
				&&!(user!=null&&(requestURI.startsWith("/login")||requestURI.startsWith("/registration")))) {
			return true;
		} else {
			return false;
		}
	}
	

	public boolean isProtected(String requestURI) {
		for (String i : PROT_URI) {
			if (requestURI.startsWith(i)) {
				return true;
			}
		}
		return false;
	}

}
