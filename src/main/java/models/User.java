package models;

import java.io.Serializable;
import java.sql.Timestamp;

import models.forms.Form;

public class User extends Form implements Serializable {
	private static final long serialVersionUID = 8719237595683164604L;
	
	private String password;
	private Timestamp created;
	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return getLastName()+" "+getFirstName()+" "+getMiddleName();
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
		
	}
	
	
	
}
