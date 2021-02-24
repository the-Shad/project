package dao;

import java.util.ArrayList;

import exceptions.ApplicationException;
import models.Answer;
import models.Question;
import models.Test;

public interface TestDao {
	public Test getTest(Long id) throws ApplicationException;

	public ArrayList<Test> getByPage(int page) throws ApplicationException;

	public ArrayList<Test> getByPageAndAuthorId(int page, Long authorId) throws ApplicationException;

	public long countTests() throws ApplicationException;

	public long countTests(Long authorId) throws  ApplicationException ;

	public void createTest(Long userId, String name, String description);

	public void createQuestion(Question question);

	public void createAnswer(Answer answer);

	public void editTest(Long id, String name, String description);

	public void editQuestion(Long id, String question, Boolean multiResponse);

	public void deleteQuestion(Long id);

	public void editAnswer(Long id, String answer, Boolean correct);

	public void deleteAnswer(Long id);

	public Answer getAnswer(Long id);

	public Question getQuestion(Long id);
	
	public void deleteTest(Long id);

	public ArrayList<Test> search(String search, int page);
	
	public ArrayList<Test> searchTut(String search, int page, Long id);
}
