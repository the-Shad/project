package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import context.ApplicationContext;

@WebListener
public class ApplicationContextListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext.getApplicationContext(sce.getServletContext());
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ApplicationContext.getApplicationContext(sce.getServletContext()).shutDown();
	}
}
