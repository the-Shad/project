package services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;

import dao.ResultDao;
import dao.TestDao;
import exceptions.ApplicationException;
import models.Answer;
import models.Question;
import models.Result;
import models.Test;
import services.TutorService;
import utils.ConnectionUtil;

public class TutorServiceImpl implements TutorService {
	private BasicDataSource dataSource;
	private TestDao testDao;
	private ResultDao resultDao;

	public TutorServiceImpl(BasicDataSource dataSource, TestDao testDao, ResultDao resultDao) {
		this.dataSource = dataSource;
		this.testDao = testDao;
		this.resultDao = resultDao;
	}
	
	
	public void createTest(Long userId, String name, String description) {
		if(StringUtils.isBlank(name)) {
			throw new ApplicationException("tutor.page.errorName");
		}
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.createTest(userId, name, description);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
	}
	
	@Override
	public void createQuestion(Question question) {
		if(StringUtils.isBlank(question.getQuestion())) {
			throw new ApplicationException("tutor.page.errorQuestion");
		}
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.createQuestion(question);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public void createAnswer(Answer answer) {
		if(StringUtils.isBlank(answer.getAnswer())) {
			throw new ApplicationException("tutor.page.errorAnswer");
		}
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.createAnswer(answer);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
	}

	public ArrayList<Result> getAdvancedResultList(int page) {
		ArrayList<Result> res = null;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = resultDao.getAdvTestsResults(page);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	@Override
	public ArrayList<Test> getTutorTestList(Long id, int page) {
		ArrayList<Test> res = null;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = testDao.getByPageAndAuthorId(page, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	@Override
	public ArrayList<Test> getAdvancedTestList(int page) {
		ArrayList<Test> res = null;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = testDao.getByPage(page);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	@Override
	public ArrayList<Result> getTutorResultList(Long authorId, int page) {
		ArrayList<Result> res = null;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = resultDao.getTestsResults(authorId, page);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	public int pagesTests() {
		int pages = 0;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			long l = testDao.countTests();
			pages = (int) Long.divideUnsigned(l, 40);
			if (l % 40 > 0) {
				pages++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return pages;
	}

	public int pagesResults() {
		int pages = 0;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			long l = resultDao.countResults();
			pages = (int) Long.divideUnsigned(l, 40);
			if (l % 40 > 0) {
				pages++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return pages;
	}

	public int pagesTutorTests(Long authorId) {
		int pages = 0;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			long l = testDao.countTests(authorId);
			pages = (int) Long.divideUnsigned(l, 40);
			if (l % 40 > 0) {
				pages++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return pages;
	}

	public int pagesTutorResults(Long authorId) {
		int pages = 0;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			long l = resultDao.countResults(authorId);
			pages = (int) Long.divideUnsigned(l, 40);
			if (l % 40 > 0) {
				pages++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return pages;
	}


	@Override
	public void editTest(Long id, String name, String description) {
		if(StringUtils.isBlank(name)) {
			throw new ApplicationException("tutor.page.errorName");
		}
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.editTest(id, name, description);
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public Question getQuestion(Long id) {
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			Question res;
			res = testDao.getQuestion(id);
			return res;
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public void editQuestion(Long id, String question, Boolean multiResponse) {
		if(StringUtils.isBlank(question)) {
			throw new ApplicationException("tutor.page.errorQuestion");
		}
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.editQuestion(id, question, multiResponse);
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public void deleteQuestion(Long id) {
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.deleteQuestion(id);
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public Answer getAnswer(Long id) {
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			Answer res;
			res = testDao.getAnswer(id);
			return res;
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public void editAnswer(Long id, String answer, Boolean correct) {
		if(StringUtils.isBlank(answer)) {
			throw new ApplicationException("tutor.page.errorAnswer");
		}
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.editAnswer(id, answer, correct);
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public void deleteAnswer(Long id) {
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.deleteAnswer(id);
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public Test getTest(Long id) {
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			Test res;
			res = testDao.getTest(id);
			return res;
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public void deleteTest(Long id) {
		try(Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			testDao.deleteTest(id);
		}catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
	}


	@Override
	public ArrayList<Test> searchTutor(String search, int page, Long id) {
		ArrayList<Test> res = new ArrayList<>();
		try(Connection c = dataSource.getConnection()){
			ConnectionUtil.setConnection(c);
			res = testDao.searchTut(search, page, id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}finally {
			ConnectionUtil.clearConnection();
		}
		return res;
 	}


}
