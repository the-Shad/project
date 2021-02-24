package dao;

import java.util.ArrayList;

import models.News;

public interface NewsDao {
	
	public void delete(Long id);

	public long countNews();

	public ArrayList<News> getList(int page);

	public News getById(Long id);

	public void update(News news);

	public void create(News news);

}
