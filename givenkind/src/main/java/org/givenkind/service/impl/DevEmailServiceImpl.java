package org.givenkind.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.givenkind.dto.PasswordResetAuthorizationDTO;
import org.givenkind.model.CompletedTransactions;
import org.givenkind.model.PasswordReset;
import org.givenkind.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevEmailServiceImpl implements EmailService {

	
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public Boolean ngoRegistrationEmail(String email) {
		log.info("ngoRegistrationEmail: email="+email);
		return true;
	}

	@Override
	public Boolean adminRegistrationEmail(String profileURL) {
		log.info("adminRegistrationEmail: profileURL="+profileURL);
		return true;
	}

	@Override
	public Boolean donorRegistrationEmail(String email) {
		log.info("donorRegistrationEmail: email="+email);
		return true;
	}

	@Override
	public Boolean donorItemEmail(CompletedTransactions completedTransactions, String email) {
		log.info("donorItemEmail: donatedItem="+completedTransactions.toString()+" email="+email);
		return true;
	}

	@Override
	public Boolean forgotPasswordEmail(PasswordResetAuthorizationDTO pr) {
		try {
			log.info("password reset link is: "+pr.getPasswordResetLink());
			return true;
		} catch (Exception ex) {
			log.error("could not send password reset email", ex);
			return false;
		}
	}
}
