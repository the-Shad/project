package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import dao.rsh.RSHFactory;
import exceptions.ApplicationException;
import models.News;
import utils.ConnectionUtil;

public class NewsDaoImpl implements NewsDao {

	private QueryRunner qr = new QueryRunner();

	public void delete(Long id) {
		try {
			Connection c = ConnectionUtil.getConnection();
			qr.update(c, "DELETE FROM news WHERE id = ?", id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public long countNews() {
		Long res = null;
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM news");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getLong(1);
			}
			return res;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public ArrayList<News> getList(int page) {
		ArrayList<News> res = new ArrayList<>();
		try {
			Connection c = ConnectionUtil.getConnection();
			res.addAll(qr.query(c, "SELECT * FROM news ORDER BY id DESC LIMIT ? OFFSET ?", RSHFactory.getArrayRSH(RSHFactory.NEWS_RSH), 15,
					15 * (page - 1)));

			return res;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public News getById(Long id) {
		try {
			Connection c = ConnectionUtil.getConnection();
			News res = qr.query(c, "SELECT * FROM news WHERE id = ?", RSHFactory.getSingleRSH(RSHFactory.NEWS_RSH), id);

			return res;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public void update(News news) {
		try {
			qr.update(ConnectionUtil.getConnection(), "UPDATE news SET name = ?, news = ? WHERE  id = ?",
					news.getName(), news.getNews(), news.getId());
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void create(News news) {
		try {
			qr.update(ConnectionUtil.getConnection(),
					"INSERT INTO news VALUES (nextval('news_id_seq'), ?, ?)",
					news.getName(), news.getNews());
		} catch (SQLException e) {
		}
	}
}
