package services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.RandomStringUtils;

import dao.UserDao;
import exceptions.ApplicationException;
import models.User;
import models.forms.RegistrationForm;
import services.PasswordCipherService;
import services.RegistrationService;
import utils.ConnectionUtil;

public class RegistrationServiceImpl implements RegistrationService {
	private BasicDataSource dataSource;
	private UserDao userDao;
	private PasswordCipherService passwordService;

	@Override
	public void registration(RegistrationForm form) throws ApplicationException {
		checkUsers();
		if (!form.getRepeatPassword().equals(form.getPassword())) {
			throw new ApplicationException("registration.page.errorPass");
		} else if (form.getLogin().length() <= 3) {
			throw new ApplicationException("registration.page.errorLogin");
		}
		form.setPassword(passwordService.cipher(form.getPassword()));
		try (Connection c = dataSource.getConnection()) {
			c.setAutoCommit(false);
			ConnectionUtil.setConnection(c);
			Long id = userDao.registration(form);
			if (id != null) {
				userDao.insertRole(id, 3L);
				String token = generateToken();
				userDao.insertToken(id, token);
				sendConfirmKey(form.getEmail(), token);
			}
			c.commit();
		} catch (SQLException e) {
			try {
				ConnectionUtil.getConnection().rollback();
			} catch (SQLException e1) {
			}
		} finally {
			ConnectionUtil.clearConnection();
		}
	}

	public RegistrationServiceImpl(BasicDataSource dataSource, UserDao userDao, PasswordCipherService passwordService) {
		this.dataSource = dataSource;
		this.userDao = userDao;
		this.passwordService = passwordService;
	}

	@Override
	public String generateToken() {
		String token = RandomStringUtils.randomAlphanumeric(100).toString();
		return token;
	}

	@Override
	public void activation(String token) {
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			Long id = userDao.getRegistrationId(token);
			if (id != null) {
				userDao.activateUser(id);
			}
		} catch (SQLException e) {
		} finally {
			ConnectionUtil.clearConnection();
		}
	}

	private void sendConfirmKey(String email, String token) {
		System.out.println("/confirm?token=" + token);
	}

	private void sendForgotKey(String email, String token) {
		System.out.println("/change_password?token=" + token);
	}

	@Override
	public void forgotPassword(String email) {
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			c.setAutoCommit(false);
			User user = userDao.getByLoginOrEmail(email);
			if (user.equals(null)) {
				throw new ApplicationException("forgot.page.errorExist");
			}
			String token = generateToken();
			userDao.insertPasswordToken(user.getId(), token);
			c.commit();
			sendForgotKey(email, token);
		} catch (SQLException e) {
			try {
				ConnectionUtil.getConnection().rollback();
			} catch (SQLException e1) {
			}
			throw new ApplicationException(e.getMessage());
		} finally {
			ConnectionUtil.clearConnection();
		}

	}

	@Override
	public void changePassword(String token, String password, String repeatPassword) {
		if (!password.equals(repeatPassword)) {
			throw new ApplicationException("registration.page.errorPass");
		}
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			c.setAutoCommit(false);
			Long id = userDao.getPasswordId(token);
			userDao.changePassword(id, passwordService.cipher(password));
			c.commit();
		} catch (SQLException e) {
			try {
				ConnectionUtil.getConnection().rollback();
			} catch (SQLException e1) {
			}
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}

	public void checkUsers() {
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			c.setAutoCommit(false);
			ArrayList<Long> ids = userDao.getRegistrationIds();
			Long date = new Timestamp(System.currentTimeMillis()).getTime();
			for(Long id: ids) {
				User user = userDao.getByID(id);
				Long userDate = user.getCreated().getTime();
				if(!user.isActive()&&(date<=(userDate+1000*60*60*24))) {
					userDao.delete(id);
				}
			}
			c.commit();
		} catch (SQLException e) {
			try {
				ConnectionUtil.getConnection().rollback();
			} catch (SQLException e1) {
				throw new ApplicationException(e1);
			}
		} finally {
			ConnectionUtil.clearConnection();
		}
	}

}
