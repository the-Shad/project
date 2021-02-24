package dao;

import static dao.DAO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;

import dao.rsh.RSHFactory;
import exceptions.ApplicationException;
import models.Answer;
import models.Question;
import models.Test;
import utils.ConnectionUtil;

public class TestDaoImpl implements TestDao {
	QueryRunner qr = new QueryRunner();

	public Test getTest(Long id) throws ApplicationException {
		try {
			Test test = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM test WHERE id = ? ",
					RSHFactory.getSingleRSH(RSHFactory.TEST_RSH), id);
			test.setQuestions(qr.query(ConnectionUtil.getConnection(), "SELECT * FROM question WHERE test_id = ? ",
					RSHFactory.getArrayRSH(RSHFactory.QUESTION_RSH), id));
			for (Question qm : test.getQuestions()) {
				qm.setAnswers(qr.query(ConnectionUtil.getConnection(), "SELECT * FROM answer WHERE question_id = ? ",
						RSHFactory.getArrayRSH(RSHFactory.ANSWER_RSH), qm.getId()));
			}
			return test;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public Test getByID(Long id) throws ApplicationException {
		try {
			Test test = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM test WHERE id = ? ",
					RSHFactory.getSingleRSH(RSHFactory.TEST_RSH), id);
			return test;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public ArrayList<Test> getByAuthorID(Long id) throws ApplicationException {
		try {
			ArrayList<Test> test = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM test WHERE user_id = ? ",
					RSHFactory.getArrayRSH(RSHFactory.TEST_RSH), id);
			return test;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public ArrayList<Test> getByPage(int page) throws ApplicationException {
		try {
			ArrayList<Test> ts = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM test " + LIMIT,
					RSHFactory.getArrayRSH(RSHFactory.TEST_RSH), 40, 40 * (page - 1));
			return ts;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}

	}

	public ArrayList<Test> getByPageAndAuthorId(int page, Long authorId) throws ApplicationException {
		try {
			ArrayList<Test> ts = qr.query(ConnectionUtil.getConnection(),
					"SELECT * FROM test WHERE user_id = ? " + LIMIT, RSHFactory.getArrayRSH(RSHFactory.TEST_RSH),
					authorId, 40, 40 * (page - 1));
			return ts;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}

	}

	public void deleteTest(Long id) throws ApplicationException {
		try {
			Connection c = ConnectionUtil.getConnection();
			qr.update(c, "DELETE FROM test WHERE id = ?", id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public long countTests() throws ApplicationException {
		Long res = null;
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM test ");
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
	public long countTests(Long authorId) throws ApplicationException {
		Long res = null;
		try {
			Connection c = ConnectionUtil.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM test WHERE user_id = ?");
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
	public void createTest(Long userId, String name, String description) {
		try {
			qr.execute(ConnectionUtil.getConnection(), "INSERT INTO test VALUES (nextval('test_id_seq'), ?, ?, ?)",
					userId, name, description);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void createQuestion(Question question) {
		try {
			qr.execute(ConnectionUtil.getConnection(),
					"INSERT INTO question VALUES (nextval('question_id_seq'), ?, ?, ?)", question.getTestId(),
					question.getQuestion(), question.isMultiResponse());
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void createAnswer(Answer answer) {
		try {
			qr.execute(ConnectionUtil.getConnection(), "INSERT INTO answer VALUES (nextval('answer_id_seq'), ?, ?, ?)",
					answer.getQuestionId(), answer.getAnswer(), answer.isCorrect());
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void editTest(Long id, String name, String description) {
		try {
			qr.update(ConnectionUtil.getConnection(), "UPDATE test SET test_name = ?, description = ? WHERE  id = ?", name,
					description, id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void editQuestion(Long id, String question, Boolean multiResponse) {
		try {
			qr.update(ConnectionUtil.getConnection(),
					"UPDATE question SET question = ?, multi_response = ? WHERE  id = ?", question, multiResponse, id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void deleteQuestion(Long id) {
		try {
			Connection c = ConnectionUtil.getConnection();
			qr.update(c, "DELETE FROM question WHERE id = ?", id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void editAnswer(Long id, String answer, Boolean correct) {
		try {
			qr.update(ConnectionUtil.getConnection(), "UPDATE answer SET answer = ?, is_correct = ? WHERE  id = ?",
					answer, correct, id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public void deleteAnswer(Long id) {
		try {
			Connection c = ConnectionUtil.getConnection();
			qr.update(c, "DELETE FROM answer WHERE id = ?", id);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public Answer getAnswer(Long id) {
		try {
			Answer answer;
			answer = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM answer WHERE id = ? ",
					RSHFactory.getSingleRSH(RSHFactory.ANSWER_RSH), id);
			return answer;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public Question getQuestion(Long id) {
		try {
			Question question;
			question = qr.query(ConnectionUtil.getConnection(), "SELECT * FROM question WHERE id = ? ",
					RSHFactory.getSingleRSH(RSHFactory.QUESTION_RSH), id);
			question.setAnswers(qr.query(ConnectionUtil.getConnection(), "SELECT * FROM answer WHERE question_id = ? ",
					RSHFactory.getArrayRSH(RSHFactory.ANSWER_RSH), question.getId()));
			return question;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	@Override
	public ArrayList<Test> search(String search, int page) {
		try {
			ArrayList<Test> tests = new ArrayList<>();
			int i = 40;
			if (StringUtils.isNumeric(search) && (page == 1)) {
				tests.add(getByID(Long.parseLong(search)));
				i--;
			}
			search = "%" + search + "%";
			tests.addAll(qr.query(ConnectionUtil.getConnection(),
					"SELECT * FROM test WHERE test_name LIKE ? OR description LIKE ? " + LIMIT,
					RSHFactory.getArrayRSH(RSHFactory.TEST_RSH), search, search, i, i * (page - 1)));
			return tests;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public ArrayList<Test> searchTut(String search, int page, Long id) {
		try {
			ArrayList<Test> tests = new ArrayList<>();
			int i = 40;
			if (StringUtils.isNumeric(search) && (page == 1)) {
				Test test = getByID(Long.parseLong(search));
				if (test.getAuthorId() == id) {
					tests.add(test);
					i--;
				}
			}
			search = "%" + search + "%";
			tests.addAll(qr.query(ConnectionUtil.getConnection(),
					"SELECT * FROM test WHERE user_id = ? AND (test_name LIKE ? OR description LIKE ?) " + LIMIT,
					RSHFactory.getArrayRSH(RSHFactory.TEST_RSH), id, search, search, i, i * (page - 1)));
			return tests;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}
}
