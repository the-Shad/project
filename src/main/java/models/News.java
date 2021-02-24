package models;

import java.sql.Timestamp;

public class News {
	
	private Long id;
	private String name;
	private String news;
	private Timestamp created;
	
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getNews() {
		return news;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
}
