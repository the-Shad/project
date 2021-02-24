package services.impl;

import services.PasswordCipherService;

public class PasswordCipherServiceImpl implements PasswordCipherService{
	

	@Override
	public boolean comaparePasswords(String pass, String codedPassword) {
		return pass.equals(codedPassword);
	}

	@Override
	public String cipher(String password) {
		return password;
	}

}
