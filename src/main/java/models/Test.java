package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable{
	private static final long serialVersionUID = -3980462985600720205L;
	
	private long id;
	private String name;
	private String description;
	private long authorId;
	private ArrayList<Question> questions;
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public long getAuthorId() {
		return authorId;
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	public int correctAnswersNumber() {
		int i = 0;
		for(Question q: questions) {
			i += q.correctAnswersNumber();
		}
		return i;
	}
	public Question getCurrent(int queue) {
		if(queue>=questions.size()||
				queue<0) {
			return null;
		}
		return questions.get(queue);
	}
	public Question getNext(int queue) {
		return getCurrent(queue+1);
	}

}
