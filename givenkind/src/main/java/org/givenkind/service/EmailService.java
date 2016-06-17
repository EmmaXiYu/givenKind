package org.givenkind.service;

import org.givenkind.dto.PasswordResetAuthorizationDTO;
import org.givenkind.model.CompletedTransactions;
import org.givenkind.model.PasswordReset;

public interface EmailService {

	public Boolean ngoRegistrationEmail(String email);
	public Boolean adminRegistrationEmail(String profileURL);
	public Boolean donorRegistrationEmail(String email);
	public Boolean donorItemEmail(CompletedTransactions completedTransactions, String email);
	public Boolean forgotPasswordEmail(PasswordResetAuthorizationDTO pr);
}
