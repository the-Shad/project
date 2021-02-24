package filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter(filterName = "ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(req, resp);
		}catch (Throwable e) {
			resp.sendRedirect("/home");
		}
	}
}
