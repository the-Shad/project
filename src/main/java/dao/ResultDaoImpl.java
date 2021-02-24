package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.dbutils.QueryRunner;

import dao.rsh.RSHFactory;
import exceptions.ApplicationException;
import models.Result;
import models.Test;
import models.User;

import static dao.DAO.*;
import utils.ConnectionUtil;

public class ResultDaoImpl implements ResultDao {
	private QueryRunner qr = new QueryRunner();

	public void voidInsertResult(User user, Test test, int res) {
		try (Connection c = ConnectionUtil.getConnection()) {
			qr.update(c, "INSERT INTO result ( id, user_id, author_id, test_name, result, user_name) VALUES (nextval('result_id_seq'), ?, ?, ?, ?, ?)", 
					user.getId(), test.getAuthorId(), test.getName(), res, user.getFullName());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Result> getTestsResults(Long authorId, int page) {
		ArrayList<Result> res = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnection()) {
			res = qr.query(c, "SELECT * FROM result WHERE author_id = ? " + LIMIT,
					RSHFactory.getArrayRSH(RSHFactory.RESULT_RSH), authorId, 40, (page - 1) * 40);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Result> getAdvTestsResults(int page) {
		ArrayList<Result> res = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnection()) {
			res = qr.query(c, "SELECT * FROM result" + LIMIT, RSHFactory.getArrayRSH(RSHFactory.RESULT_RSH), 40,
					(page - 1) * 40);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public long countResults() {
		Long res = null;
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM result ");
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
	public long countResults(Long authorId) {
		Long res = null;
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM result WHERE author_id = ?");
			ps.setLong(1, authorId);
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
	public long countUserResults(Long userId) {
		Long res = null;
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM result WHERE user_id = ?");
			ps.setLong(1, userId);
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
	public ArrayList<Result> getStudTestsResults(Long id, int page) {
		ArrayList<Result> res = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnection()) {
			res = qr.query(c, "SELECT * FROM result WHERE user_id = ? " + LIMIT,
					RSHFactory.getArrayRSH(RSHFactory.RESULT_RSH), id, 40, (page - 1) * 40);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
