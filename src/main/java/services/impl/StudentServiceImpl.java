package services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import dao.ResultDao;
import dao.TestDao;
import models.Result;
import models.Test;
import models.User;
import services.StudentService;
import utils.ConnectionUtil;

public class StudentServiceImpl implements StudentService {

	private BasicDataSource dataSource;
	private TestDao testDao;
	private ResultDao resultDao;

	public StudentServiceImpl(BasicDataSource dataSource, TestDao testDao, ResultDao resultDao) {
		this.dataSource = dataSource;
		this.testDao = testDao;
		this.resultDao = resultDao;
	}

	public void setResult(User user, Test test, ArrayDeque<Float> results) {
		int res = Math.round(sum(results) * 100);
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			resultDao.voidInsertResult(user, test, res);
		} catch (SQLException e) {
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	public ArrayList<Test> getTestsbySearch(String search, int page) {
		ArrayList<Test> res = new ArrayList<>();
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = testDao.search(search, page);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	public Test getTestsbyId(Long id) {
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			return testDao.getTest(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return null;
	}

	private float sum(ArrayDeque<Float> col) {
		float res = 0;
		for (Float f : col) {
			res += f;
		}
		res /= col.size();
		if (res < 0) {
			return 0;
		}
		return res;
	}

	@Override
	public ArrayList<Result> getResults(Long id, int page) {
		ArrayList<Result> res = null;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = resultDao.getStudTestsResults(id, page);
		} catch (SQLException e) {
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}
	public int pagesStudentResults(Long userId) {
		int pages = 0;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			long l = resultDao.countUserResults(userId);
			pages = (int) Long.divideUnsigned(l, 40);
			if (l % 40 > 0) {
				pages++;
			}
		} catch (SQLException e) {
		} finally {
			ConnectionUtil.clearConnection();
		}
		return pages;
	}
}
