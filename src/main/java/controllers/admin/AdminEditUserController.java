package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractController;
import exceptions.ApplicationException;
import models.User;
import models.forms.EditForm;
import services.CommonService;

@WebServlet("/admin/edit")
public class AdminEditUserController extends AbstractController {

	private static final long serialVersionUID = -4176858213946270159L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("user_id"));
		req.setAttribute(FORM, formById(id));
		req.setAttribute(PAGE_TITLE, "page.name.adminEditUser");
		forwardToPage("admin/editUser.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EditForm form = editForm(req);
		try {
			getAdminService().editUser(form);
			resp.sendRedirect("/admin");
		} catch (ApplicationException e) {
			setError(req, e);
			req.setAttribute(FORM, form);
			req.setAttribute(PAGE_TITLE, "page.name.adminEditUser");
			forwardToPage("admin/editUser.jsp", req, resp);
		}

	}

	private EditForm editForm(HttpServletRequest req) {
		EditForm form = new EditForm();
		form.setId(Long.parseLong(req.getParameter("user_id")));
		form.setLogin(req.getParameter("login"));
		form.setEmail(req.getParameter("email"));
		form.setFirstName(req.getParameter("firstName"));
		form.setLastName(req.getParameter("lastName"));
		form.setMiddleName(req.getParameter("middleName"));
		form.setActive(Boolean.parseBoolean(req.getParameter("active")));
		ArrayList<Long> roles = new ArrayList<Long>(Arrays.asList(req.getParameterValues("role")).stream().map((s) -> Long.parseLong(s))
				.collect(Collectors.toList()));
		form.setRoles(roles);
		return form;
	}

	private EditForm formById(Long id) {
		CommonService cs = getCommonService();
		User user = getAdminService().getUserById(id);
		EditForm form = new EditForm();
		form.setId(user.getId());
		form.setLogin(user.getLogin());
		form.setEmail(user.getEmail());
		form.setFirstName(user.getFirstName());
		form.setMiddleName(user.getMiddleName());
		form.setLastName(user.getLastName());
		form.setActive(user.isActive());
		form.setAdmin(cs.isAdmin(user));
		form.setAdvanced(cs.isAdvTutor(user));
		form.setTutor(cs.isTutor(user));
		form.setStudent(cs.isStudent(user));
		return form;
	}

}
