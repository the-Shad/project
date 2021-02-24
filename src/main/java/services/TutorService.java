package services;

import java.util.ArrayList;

import models.Answer;
import models.Question;
import models.Result;
import models.Test;

public interface TutorService {
	public ArrayList<Test> getAdvancedTestList(int page);

	public ArrayList<Test> getTutorTestList(Long id, int page);

	public ArrayList<Result> getAdvancedResultList(int page);

	public ArrayList<Result> getTutorResultList(Long authorId, int page);
	
	public ArrayList<Test> searchTutor(String search, int page, Long id);

	public int pagesTests();

	public int pagesResults();

	public int pagesTutorTests(Long authorId);

	public int pagesTutorResults(Long authorId);
	
	public void createTest(Long userId, String name, String description);

	public void createQuestion(Question question);

	public void createAnswer(Answer answer);

	public void editTest(Long id, String name, String description);

	public Question getQuestion(Long id);

	public void editQuestion(Long id, String question, Boolean multiResponse);

	public Answer getAnswer(Long id);

	public void editAnswer(Long id, String answer, Boolean correct);

	public void deleteAnswer(Long id);

	public void deleteQuestion(Long id);
	
	public void deleteTest(Long id);
	
	public Test getTest(Long id);
}
