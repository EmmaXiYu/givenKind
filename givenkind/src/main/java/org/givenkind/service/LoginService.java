package org.givenkind.service;

import org.givenkind.dto.ForgotPasswordDTO;
import org.givenkind.dto.LoginDTO;
import org.givenkind.dto.PasswordResetDTO;
import org.givenkind.model.UserLogon;

public interface LoginService {

	public LoginDTO prepareLoginPage();
	
	public LoginDTO login(LoginDTO loginDTO);
	
	public void forgotPassword(ForgotPasswordDTO dto) throws NoSuchUserException;

	public UserLogon findUserByEmail(String email);

	public void resetPassword(PasswordResetDTO dto) throws NoSuchPasswordResetAuthorizationException;
	
}
