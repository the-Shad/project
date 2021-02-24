package services;

import exceptions.ApplicationException;
import models.User;
import models.forms.Form;

public interface CommonService {

	User login(String login, String password) throws ApplicationException;

	public boolean isAdmin(Form user);

	public boolean isAdvTutor(Form user);

	public boolean isTutor(Form user);

	public boolean isStudent(Form user);

}
