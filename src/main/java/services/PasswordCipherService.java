package services;

public interface PasswordCipherService {
	
	boolean comaparePasswords(String pass, String codedPassword);
	String cipher(String password);

}
