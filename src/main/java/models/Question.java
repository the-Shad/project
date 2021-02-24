package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
	private static final long serialVersionUID = -4255748933076940083L;

	private long id;
	private long testId;
	private String question;
	private boolean multiResponse;
	private ArrayList<Answer> answers;

	public long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public boolean isMultiResponse() {
		return multiResponse;
	}

	public void setMultiResponse(boolean multiResponse) {
		this.multiResponse = multiResponse;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}
	public int correctAnswersNumber() {
		int i = 0;
		for(Answer an: answers) {
			if(an.isCorrect()) {
				i++;
			}
		}
		return i;
	}
	public Answer getByAId(Long id) {
		for(Answer answer: answers) {
			if(answer.getId() == id) {
				return answer;
			}
		}
		return null;
	}
}
