package dao.rsh;

import static dao.DAO.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.ResultSetHandler;

import models.Answer;
import models.News;
import models.Question;
import models.Result;
import models.Test;
import models.Token;
import models.User;

public class RSHFactory {

	public final static ResultSetHandler<User> USER_RSH = new ResultSetHandler<User>() {
		@Override
		public User handle(ResultSet rs) throws SQLException {
			User user = new User();
			user.setId(rs.getLong(ID));
			user.setLogin(rs.getString(LOGIN));
			user.setEmail(rs.getString(EMAIL));
			user.setPassword(rs.getString(PASSWORD));
			user.setFirstName(rs.getString(FIRST_NAME));
			user.setLastName(rs.getString(LAST_NAME));
			user.setMiddleName(rs.getString(MIDDLE_NAME));
			user.setActive(rs.getBoolean(ACTIVE));
			user.setCreated(rs.getTimestamp(CREATED));
			return user;
		}
	};
	
	public final static ResultSetHandler<Token> TOKEN_RSH = new ResultSetHandler<Token>() {
		@Override
		public Token handle(ResultSet rs) throws SQLException {
			Token token = new Token();
			token.setUserId(rs.getLong(ID));
			token.setToken(rs.getString(TOKEN));
			return token;
		}
	};
	
	public final static ResultSetHandler<News> NEWS_RSH = new ResultSetHandler<News>() {
		
		@Override
		public News handle(ResultSet rs) throws SQLException {
			News news = new News();
			news.setId(rs.getLong(ID));
			news.setName(rs.getString(NAME));
			news.setNews(rs.getString(NEWS));
			news.setCreated(rs.getTimestamp(CREATED));
			return news;
		}
	};
	
	public final static ResultSetHandler<Test> TEST_RSH = new ResultSetHandler<Test>() {
		@Override
		public Test handle(ResultSet rs) throws SQLException {
			Test test = new Test();
			test.setId(rs.getLong(ID));
			test.setAuthorId(rs.getLong(USER_ID));
			test.setName(rs.getString(TEST_NAME));
			test.setDescription(rs.getString(DESCRIPTION));
			return test;
		}
	};

	public final static ResultSetHandler<Question> QUESTION_RSH = new ResultSetHandler<Question>() {
		@Override
		public Question handle(ResultSet rs) throws SQLException {
			Question quest = new Question();
			quest.setId(rs.getLong(ID));
			quest.setMultiResponse(rs.getBoolean(MULTI_RESPONCE));
			quest.setQuestion(rs.getString(QUESTION));
			quest.setTestId(rs.getLong(TEST_ID));
			return quest;
		}
	};
	
	public final static ResultSetHandler<Long> USER_ROLES_RSH = new ResultSetHandler<Long>() {
		@Override
		public Long handle(ResultSet rs) throws SQLException {
			return rs.getLong(ROLE_ID);
		}
	};
	
	public final static ResultSetHandler<Answer> ANSWER_RSH = new ResultSetHandler<Answer>() {
		@Override
		public Answer handle(ResultSet rs) throws SQLException {
			Answer answer = new Answer();
			answer.setId(rs.getLong(ID));
			answer.setAnswer(rs.getString(ANSWER));
			answer.setCorrect(rs.getBoolean(IS_CORRECT));
			return answer;
		}
	};
	
	public final static ResultSetHandler<Result> RESULT_RSH = new ResultSetHandler<Result>() {
		@Override
		public Result handle(ResultSet rs) throws SQLException {
			Result res = new Result();
			res.setTestName(rs.getString(TEST_NAME));
			res.setAuthorId(rs.getLong(AUTHOR_ID));
			res.setId(rs.getLong(ID));
			res.setStudentId(rs.getLong(USER_ID));
			res.setResult(rs.getInt(RESULT));
			res.setStudentName(rs.getString("user_name"));
			return res;
		}
	};
	
	
	public final static <T> ResultSetHandler<T> getSingleRSH(
			final ResultSetHandler<T> oneRowRSH){
		return new ResultSetHandler<T>() {
			@Override
			public T handle(ResultSet rs) throws SQLException {
				if(rs.next()) {
					return oneRowRSH.handle(rs);
				}else {
					return null;				
				}
			}
		};
	}
	
	public final static  <T> ResultSetHandler<ArrayList<T>>  getArrayRSH(
			final ResultSetHandler<T> oneRowRSH){
		return new ResultSetHandler<ArrayList<T>>() {;
			@Override
			public ArrayList<T> handle(ResultSet rs) throws SQLException {
				ArrayList<T> list = new ArrayList<T>();
				while(rs.next()) {
					list.add(oneRowRSH.handle(rs));
				}
				return list;
			}
		};
	};
	
}
