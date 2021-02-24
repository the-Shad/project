package filters;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter extends AbstractFilter {

	private static final String LANG_PARAM = "lang";
	public static final String SESSION_LOCALE = "SESSION_LOCALE";
	
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String userLang = req.getParameter(LANG_PARAM);
		Locale sessionLocale = (Locale) req.getSession().getAttribute(SESSION_LOCALE);
		if (StringUtils.isNotBlank(userLang)) {
			sessionLocale = new Locale(userLang);
		}
		
		if (sessionLocale == null) {
			sessionLocale = req.getLocale();
		}
		req.getSession().setAttribute(SESSION_LOCALE, sessionLocale);
		chain.doFilter(req, resp);

	}

}
