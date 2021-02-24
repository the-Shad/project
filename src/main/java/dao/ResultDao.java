package dao;

import java.util.ArrayList;

import models.Result;
import models.Test;
import models.User;

public interface ResultDao {
	public void voidInsertResult(User user, Test test, int res);
	public ArrayList<Result> getTestsResults(Long authorId, int page);
	public ArrayList<Result> getAdvTestsResults(int page);
	public long countResults() ;
	public long countResults(Long authorId);
	public ArrayList<Result> getStudTestsResults(Long id, int page);
	public long countUserResults(Long userId);
	
}
