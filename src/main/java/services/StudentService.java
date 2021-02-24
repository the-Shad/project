package services;

import java.util.ArrayDeque;
import java.util.ArrayList;

import models.Result;
import models.Test;
import models.User;

public interface StudentService {
	public ArrayList<Test> getTestsbySearch(String search, int page);
	public Test getTestsbyId(Long id);
	public void setResult(User user, Test test, ArrayDeque<Float> results);
	public ArrayList<Result> getResults(Long id, int pg);
	public int pagesStudentResults(Long userId);
}
