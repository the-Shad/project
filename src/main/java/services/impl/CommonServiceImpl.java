package services.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.dbcp2.BasicDataSource;

import dao.UserDao;
import exceptions.ApplicationException;
import models.User;
import models.forms.Form;
import services.CommonService;
import services.PasswordCipherService;
import utils.ConnectionUtil;

public class CommonServiceImpl implements CommonService {
	

	public static final String ADMIN = "Admin";
	public static final String ADVTUTOR = "Advanced";
	public static final String TUTOR = "Tutor";
	public static final String STUDENT = "Student";

	private HashMap<String, Long> roleMap;
	private UserDao userDao;
	private BasicDataSource dataSource;
	private PasswordCipherService passowrdService;

	public CommonServiceImpl(HashMap<String, Long> roleMap, UserDao userDao, PasswordCipherService passowrdService,
			BasicDataSource dataSource) {
		this.userDao = userDao;
		this.passowrdService = passowrdService;
		this.dataSource = dataSource;
		this.roleMap = roleMap;
	}

	public boolean isAdmin(Form user) {
		if (user == null) {
			return false;
		}
		return user.getRoles().contains(roleMap.get(ADMIN));
	}

	public boolean isAdvTutor(Form user) {
		if (user == null) {
			return false;
		}
		return user.getRoles().contains(roleMap.get(ADVTUTOR));
	}

	public boolean isTutor(Form user) {
		if (user == null) {
			return false;
		}
		return user.getRoles().contains(roleMap.get(TUTOR));
	}

	public boolean isStudent(Form user) {
		if (user == null) {
			return false;
		}
		return user.getRoles().contains(roleMap.get(STUDENT));
	}

	public User login(String login, String password) throws ApplicationException {
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			User user = userDao.getByLoginOrEmail(login);
			if (!passowrdService.comaparePasswords(password, user.getPassword())) {
				throw new ApplicationException("login.page.errorPassword");
			}
			if (!user.isActive()) {
				throw new ApplicationException("login.page.errorActive");
			}
			return user;
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		} finally {
			ConnectionUtil.clearConnection();
		}

	}



}
