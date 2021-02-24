package services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;

import dao.NewsDao;
import dao.UserDao;
import exceptions.ApplicationException;
import models.News;
import models.User;
import models.forms.EditForm;
import services.AdminService;
import utils.ConnectionUtil;

public class AdminServiceImpl implements AdminService {

	private BasicDataSource dataSource;
	private UserDao userDao;
	private NewsDao newsDao;


	public AdminServiceImpl(UserDao userDao, BasicDataSource dataSource, NewsDao newsDao) {
		this.userDao = userDao;
		this.dataSource = dataSource;
		this.newsDao = newsDao;
	}

	public void editUser(EditForm form) {
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			c.setAutoCommit(false);
			userDao.updateUser(form);
			userDao.insertRole(form.getId(), form.getRoles().toArray(new Long[form.getRoles().size()]));
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}

	}

	public int pages() {
		int pages = 0;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			long l = userDao.countUsers();
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

	public ArrayList<User> getUserListPage(int page) {
		ArrayList<User> res = new ArrayList<>();
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = userDao.getPageList(page);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	@Override
	public void delete(Long id) {
		try(Connection c = dataSource.getConnection()){
			ConnectionUtil.setConnection(c);
			userDao.delete(id);
		} catch (SQLException e) {
		}finally {
			ConnectionUtil.clearConnection();
		}
		
	}

	@Override
	public User getUserById(Long id) {
		User res = null;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = userDao.getByID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	@Override
	public ArrayList<User> search(String search, int page) {
		ArrayList<User> res = new ArrayList<>();
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = userDao.search(search, page);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}
	
	public ArrayList<News> newsList(int page){
		ArrayList<News> res = new ArrayList<>();
		try(Connection c = dataSource.getConnection()){
			ConnectionUtil.setConnection(c);
			res = newsDao.getList(page);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}
	
	public int pagesHome() {
		int pages = 0;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			long l = newsDao.countNews();
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
	
	public void editNews(News news) {
		checkNews(news);
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			newsDao.update(news);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
	}
	
	public News getNewsById(Long id) {
		News res = null;
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			res = newsDao.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
		return res;
	}

	public void deleteNews(Long id) {
		try(Connection c = dataSource.getConnection()){
			ConnectionUtil.setConnection(c);
			newsDao.delete(id);
		} catch (SQLException e) {
		}finally {
			ConnectionUtil.clearConnection();
		}
		
	}
	public void createNews(News news) {
		checkNews(news);
		try (Connection c = dataSource.getConnection()) {
			ConnectionUtil.setConnection(c);
			newsDao.create(news);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.clearConnection();
		}
	}
	
	private void checkNews(News news) {
		if((news.getName().length()>155)||StringUtils.isBlank(news.getName())) {
			throw new ApplicationException("home.page.errorNewsName");
		}
	}
}
