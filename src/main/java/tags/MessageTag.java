package tags;

import java.io.IOException;
import java.util.Locale;

import static context.ApplicationContext.APP_CONTEXT;
import static filters.LocaleFilter.SESSION_LOCALE;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import context.ApplicationContext;

public class MessageTag extends SimpleTagSupport {

	private String key;
	@Override
	public void doTag() throws JspException, IOException {
		Locale sessionLocale = (Locale) getJspContext()
				.getAttribute(SESSION_LOCALE, PageContext.SESSION_SCOPE);
		ApplicationContext appCon = (ApplicationContext) getJspContext()
				.getAttribute(APP_CONTEXT, PageContext.APPLICATION_SCOPE);
		getJspContext().getOut().print(appCon.getI18nServise()
				.getMessage(key, sessionLocale));
		key = "";
	}
	public void setKey(String key) {
		this.key = key;
	}
}
