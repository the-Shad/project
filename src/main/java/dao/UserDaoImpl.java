package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import dao.rsh.RSHFactory;
import exceptions.ApplicationException;
import models.Token;
import models.User;
import models.forms.EditForm;
import models.forms.RegistrationForm;
import utils.ConnectionUtil;
import static dao.DAO.*;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new QueryRunner();
	private HashMap<String, Long> roleMap;

	public UserDaoImpl(HashMap<String, Long> roleMap) {
		this.roleMap = roleMap;
	}

	@Override
	public User getByLoginOrEmail(String loginOrEmail) throws ApplicationException {
		try {
			User us = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM account WHERE login = ? OR email = ?",
					RSHFactory.getSingleRSH(RSHFactory.USER_RSH), loginOrEmail, loginOrEmail);
			if (us == null) {
				throw new ApplicationException("login.page.errorLogin");
			}
			us.setRoles(getRoles(us.getId()));
			return us;
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
	}

	@Override
	public ArrayList<User> getPageList(int page) throws ApplicationException {
		try {
			ArrayList<User> us = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM account  " + LIMIT,
					RSHFactory.getArrayRSH(RSHFactory.USER_RSH), 40, 40 * (page - 1));
			return us;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public User getByID(Long id) throws ApplicationException {
		try {
			User us = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM account WHERE id = ? ",
					RSHFactory.getSingleRSH(RSHFactory.USER_RSH), id);
			if (us == null) {
				throw new ApplicationException("login.page.errorId");
			}
			us.setRoles(getRoles(id));
			return us;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public void insertRole(Long id, Long... roles) {
		ArrayList<Long> dbRoles = getRoles(id);
		for (Long role : roleMap.values()) {
			if (Arrays.asList(roles).contains(role) && !dbRoles.contains(role)) {
				try {
					qr.update(ConnectionUtil.getConnection(),
							"INSERT INTO account_role VALUES (nextval('user_role_id_seq'), ?, ?)", id, role);
				} catch (SQLException e) {
				}
			} if (!Arrays.asList(roles).contains(role) && dbRoles.contains(role)) {
				try {
					qr.update(ConnectionUtil.getConnection(),
							"DELETE FROM account_role WHERE user_id = ? AND role_id = ?", id, role);
				} catch (SQLException e) {
				}
			}
		}
	}

	public void updateUser(EditForm form) throws ApplicationException {
		try {
			qr.update(ConnectionUtil.getConnection(),
					"UPDATE account SET login = ?, email = ?, last_name = ?, first_name = ?, middle_name = ?, activity = ? WHERE  id = ?",
					toList(form));
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public Long countUsers() throws ApplicationException {
		Long res = null;
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM account");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getLong(1);
			}
			return res;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void delete(Long id) {
		try {
			qr.update(ConnectionUtil.getConnection(), "DELETE FROM account WHERE id = ?", id);
		} catch (SQLException e) {
		}
	}

	public void insertToken(Long id, String token) throws ApplicationException {
		try {

			qr.update(ConnectionUtil.getConnection(), "INSERT INTO account_registration VALUES (?, ?)", id, token);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public void activateUser(Long id) throws ApplicationException {
		try {
			qr.update(ConnectionUtil.getConnection(), "UPDATE account SET activity = TRUE WHERE  id = ?", id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public Long getRegistrationId(String token) throws ApplicationException {
		try {
			Long res = null;
			res = qr.insert(ConnectionUtil.getConnection(),
					"DELETE FROM account_registration WHERE token = ? RETURNING id", new ScalarHandler<Long>(), token);
			return res;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}

	}

	@Override
	public Long registration(RegistrationForm form) {
		Long res = null;
		try {
			res = qr.insert(ConnectionUtil.getConnection(),
					"INSERT INTO account VALUES (nextval('user_id_seq'), ?, ?, ?, ?, ?, ?, ?) RETURNING id",
					new ScalarHandler<Long>(), form.getLogin(), form.getEmail(), form.getPassword(), form.getLastName(),
					form.getFirstName(), form.getMiddleName(), form.isActive());
		} catch (SQLException e) {
		}
		return res;
	}

	public ArrayList<Long> getRoles(Long id) {
		ArrayList<Long> res = null;
		try {
			res = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM account_role WHERE user_id = ? ",
					RSHFactory.getArrayRSH(RSHFactory.USER_ROLES_RSH), id);
		} catch (SQLException e) {
		}
		return res;
	}

	public ArrayList<User> search(String search, int page) {
		try {
			ArrayList<User> us = new ArrayList<>();
			int i = 40;
			if (StringUtils.isNumeric(search) && (page == 1)) {
				try {
					us.add(getByID(Long.parseLong(search)));
				i--;
				}catch (Throwable e) {
				}
				
			}
			search = "%" + search + "%";
			us.addAll(qr.query(ConnectionUtil.getConnection(),
					"SELECT * FROM account WHERE login LIKE ? OR email LIKE ? OR last_name LIKE ? OR first_name LIKE ? OR middle_name LIKE ? "
							+ LIMIT,
					RSHFactory.getArrayRSH(RSHFactory.USER_RSH), search, search, search, search, search, i,
					i * (page - 1)));
			return us;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	private Object[] toList(EditForm form) {
		ArrayList<Object> list = new ArrayList<>();
		list.add(form.getLogin());
		list.add(form.getEmail());
		list.add(form.getLastName());
		list.add(form.getFirstName());
		list.add(form.getMiddleName());
		list.add(form.isActive());
		list.add(form.getId());
		return list.toArray();
	}

	@Override
	public void changePassword(Long id, String password) {
		if(id.equals(null)) {
			throw new ApplicationException();
		}
		try {
			qr.update(ConnectionUtil.getConnection(), "UPDATE account SET password = ? WHERE  id = ?", password, id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public Long getPasswordId(String token) {
		try {
			Long res = null;
			res = qr.insert(ConnectionUtil.getConnection(),
					"DELETE FROM account_password WHERE token = ? RETURNING id", new ScalarHandler<Long>(), token);
			return res;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public ArrayList<Long> getRegistrationIds() {
		ArrayList<Long> res = new ArrayList<>();
		try {
			ArrayList<Token> tokens = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM account_registration",
					RSHFactory.getArrayRSH(RSHFactory.TOKEN_RSH));
			for(Token token : tokens) {
				res.add(token.getUserId());
			}
		} catch (SQLException e) {
		}
		return res;
	}
	
	public void insertPasswordToken(Long id, String token) throws ApplicationException {
		try {
			qr.update(ConnectionUtil.getConnection(), "INSERT INTO account_password VALUES (?, ?)", id, token);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}
}
