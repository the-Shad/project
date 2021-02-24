package models;

import java.io.Serializable;



public class Answer implements Serializable {
	private static final long serialVersionUID = -9899819370564704L;
	
	private Long id;
	private Long questionId;
	private String answer;
	private boolean correct;
	
	public Long getId() {
		return id;
	}
	public String getAnswer() {
		return answer;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	
	

}
