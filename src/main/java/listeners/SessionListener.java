package listeners;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String id = se.getSession().getId();
		System.out.println("Session " + id + ": was created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String id = se.getSession().getId();
		System.out.println("Session " + id + ": was destroyed");
	}
}
