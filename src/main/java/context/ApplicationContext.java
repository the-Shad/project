package context;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletContext;
import org.apache.commons.dbcp2.BasicDataSource;

import dao.ResultDaoImpl;
import dao.TestDaoImpl;
import dao.UserDaoImpl;
import dao.NewsDao;
import dao.NewsDaoImpl;
import dao.ResultDao;
import dao.RoleDao;
import dao.TestDao;
import dao.UserDao;
import services.AdminService;
import services.CommonService;
import services.I18nService;
import services.PasswordCipherService;
import services.RegistrationService;
import services.StudentService;
import services.TutorService;
import services.impl.AdminServiceImpl;
import services.impl.CommonServiceImpl;
import services.impl.I18nServiceImpl;
import services.impl.PasswordCipherServiceImpl;
import services.impl.RegistrationServiceImpl;
import services.impl.StudentServiceImpl;
import services.impl.TutorServiceImpl;

public final class ApplicationContext {
	public static final String APP_CONTEXT = "APP_CONTEXT";
	
	private static final Properties applicationProperies = new Properties();
	private BasicDataSource dataSource;
	private HashMap<String, Long> roleMap;
	private UserDao userDao;
	private TestDao testDao;
	private ResultDao resultDao;
	private PasswordCipherService passwordService;
	private I18nService i18nService;
	private AdminService adminService;
	private TutorService tutorService;
	private StudentService studentService;
	private CommonService commonService;
	private RegistrationService regService;
	private NewsDao newsDao;

	private ApplicationContext() {
		loadApplicationProperties();
		dataSource = setupDataSource();
		roleMap = RoleDao.getRoleMap(dataSource);
		newsDao = new NewsDaoImpl();
		userDao = new UserDaoImpl(roleMap);
		testDao = new TestDaoImpl();
		resultDao = new ResultDaoImpl();
		passwordService = new PasswordCipherServiceImpl();
		i18nService = new I18nServiceImpl();
		adminService = new AdminServiceImpl(userDao, dataSource, newsDao);
		tutorService = new TutorServiceImpl(dataSource, testDao, resultDao);
		studentService = new StudentServiceImpl(dataSource, testDao, resultDao);
		commonService = new CommonServiceImpl(roleMap, userDao, passwordService, dataSource);
		regService = new RegistrationServiceImpl(dataSource, userDao, passwordService);
	}

	public void shutDown() {
		try {
			dataSource.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ApplicationContext getApplicationContext(ServletContext sc) {
		ApplicationContext context = (ApplicationContext) sc.getAttribute(APP_CONTEXT);
		if (context == null) {
			context = new ApplicationContext();
			sc.setAttribute(APP_CONTEXT, context);
		}
		return context;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public HashMap<String, Long> getRoleMap() {
		return roleMap;
	}

	public StudentService getStudentService() {
		return studentService;
	}
	
	private BasicDataSource setupDataSource() {
		BasicDataSource  ds = new BasicDataSource();
		ds.setDriverClassName(getAppProperties("db.driver"));
		ds.setUrl(getAppProperties("db.url"));
		ds.setUsername(getAppProperties("db.login"));
		ds.setPassword(getAppProperties("db.password"));
		ds.setInitialSize(Integer.parseInt(getAppProperties("db.init")));
		ds.setMaxTotal(Integer.parseInt(getAppProperties("db.max")));
		return ds;
	}

	public BasicDataSource getBasicDataSource() {
		return dataSource;
	}

	public I18nService getI18nServise() {
		return i18nService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public TutorService getTutorService() {
		return tutorService;
	}
	
	public RegistrationService getRegistrationService() {
		return regService;
	}
	
	private void loadApplicationProperties() {
		try(InputStream in = ApplicationContext.class.getClassLoader().getResourceAsStream("app.properties")){
			applicationProperies.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getAppProperties(String key) {
		return applicationProperies.getProperty(key);
	}

}
