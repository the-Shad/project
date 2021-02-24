package services;

import java.util.ArrayList;

import models.News;
import models.User;
import models.forms.EditForm;

public interface AdminService {

	public int pages();

	public void editUser(EditForm form);

	public ArrayList<User> getUserListPage(int page);

	public void delete(Long id);

	public User getUserById(Long id);

	public ArrayList<User> search(String search, int page);

	public ArrayList<News> newsList(int page);

	public int pagesHome();

	public void editNews(News news);

	public News getNewsById(Long id);

	public void deleteNews(Long id);
	
	public void createNews(News news);

}
