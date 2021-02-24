package services;

import exceptions.ApplicationException;
import models.forms.RegistrationForm;

public interface RegistrationService {
	
	void registration(RegistrationForm form) throws ApplicationException;
	
	String generateToken();
	
	void activation(String token);

	public void forgotPassword(String email);

	void changePassword(String token, String password, String repeatPassword);
	
	void checkUsers();
}
