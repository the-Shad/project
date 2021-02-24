package dao;


import java.util.ArrayList;

import exceptions.ApplicationException;
import models.User;
import models.forms.EditForm;
import models.forms.RegistrationForm;

public interface UserDao {

	public Long registration(RegistrationForm form);

	public User getByLoginOrEmail(String loginOrEmail) throws ApplicationException;

	public ArrayList<User> getPageList(int page) throws  ApplicationException ;

	public User getByID(Long id) throws  ApplicationException ;

	public void insertRole(Long id, Long... roles);

	public void updateUser(EditForm form) throws  ApplicationException ;

	public Long countUsers() throws  ApplicationException ;

	public void delete(Long id);

	public void insertToken(Long id, String token) throws  ApplicationException ;

	public Long getRegistrationId(String token) throws  ApplicationException ;

	public void activateUser(Long id) throws  ApplicationException ;

	public ArrayList<User> search(String search, int page);
	
	void changePassword(Long id, String password);

	public Long getPasswordId(String token);

	public ArrayList<Long> getRegistrationIds();

	public void insertPasswordToken(Long id, String token);
}
