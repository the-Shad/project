package models.forms;


import java.util.ArrayList;

public class EditForm extends Form{
	
	private Long id;
	private String email, login;
	private String firstName, middleName, lastName;
	private ArrayList<Long> roles;
	private Boolean isActive;
	private Boolean admin, advanced, tutor, student;
	

	public Boolean getAdmin() {
		return admin;
	}
	public Boolean getAdvanced() {
		return advanced;
	}
	public Boolean getTutor() {
		return tutor;
	}
	public Boolean getStudent() {
		return student;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public void setAdvanced(Boolean advanced) {
		this.advanced = advanced;
	}
	public void setTutor(Boolean tutor) {
		this.tutor = tutor;
	}
	public void setStudent(Boolean student) {
		this.student = student;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public ArrayList<Long> getRoles() {
		return this.roles;
	}
	public void setRoles(ArrayList<Long> roles) {
		this.roles = roles;
	}
	public boolean isActive() {
		return this.isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
