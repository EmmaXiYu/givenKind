package org.givenkind.service.impl;

import javax.inject.Inject;

import org.givenkind.dto.ForgotPasswordDTO;
import org.givenkind.dto.LoginDTO;
import org.givenkind.dto.PasswordResetAuthorizationDTO;
import org.givenkind.dto.PasswordResetDTO;
import org.givenkind.model.PasswordReset;
import org.givenkind.model.UserLogon;
import org.givenkind.repository.PasswordResetRepository;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.LoginService;
import org.givenkind.service.NoSuchPasswordResetAuthorizationException;
import org.givenkind.service.NoSuchUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Inject
	UserLogonRepository userLogonRepository;
	
	@Override
	public LoginDTO prepareLoginPage() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setValid(false);
		return loginDTO;
	}

	@Override
	public LoginDTO login(LoginDTO loginDTO) {
		UserLogon userLogon = userLogonRepository.findByLoginId(loginDTO.getUsername());
		if (userLogon != null && userLogon.getPasswordHash().equals(loginDTO.getPassword())) {
			loginDTO.setValid(true);
		} else {
			loginDTO.setValid(false);
		}
		return loginDTO;
	}

	@Autowired
	private PasswordResetRepository passwordResetRepo; 
	
	//@Autowired
	//private EmailService emailService;
	
	@Override
	@Transactional
	public void forgotPassword(ForgotPasswordDTO dto) throws NoSuchUserException {
		String emailAddress = dto.getEmail();
		UserLogon userLogon = userLogonRepository.findByLoginId(emailAddress);
		if(userLogon != null) {
			PasswordReset pr = new PasswordReset();
			pr.setUser(userLogon);
			pr = passwordResetRepo.save(pr);
			
			PasswordResetAuthorizationDTO prDTO = new PasswordResetAuthorizationDTO();
			prDTO.setCreatedAt(pr.getCreatedAt());
			prDTO.setId(pr.getId());
			prDTO.setUniqueResetKey(pr.getUniqueResetKey());
			prDTO.setUserEmail(userLogon.getProfile().getContactEmail());
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("id", String.valueOf(pr.getId()));
			map.add("uniqueResetKey", pr.getUniqueResetKey());
			String passwordResetLink=UriComponentsBuilder.fromPath("/resetPassword").queryParams(map).build().toUriString();
			prDTO.setPasswordResetLink(passwordResetLink);
			
			
			//emailService.forgotPasswordEmail(prDTO);
		} else {
			log.error("no such user "+emailAddress);
		}
	}
	
	@Override
	public UserLogon findUserByEmail(String email) {
		return userLogonRepository.findByLoginId(email);
	}
	
	@Inject 
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void resetPassword(PasswordResetDTO dto) throws NoSuchPasswordResetAuthorizationException {
		PasswordReset passwordReset = this.passwordResetRepo.findById(dto.getPasswordResetAuthorizationId());
		if(passwordReset == null) {
			throw new NoSuchPasswordResetAuthorizationException("no authorization with that id");
		}
		
		if(!passwordReset.getUniqueResetKey().equals(dto.getUniqueResetKey())) {
			throw new NoSuchPasswordResetAuthorizationException("reset key did not match");
		}
		
		UserLogon user = passwordReset.getUser();
		String newPassword = passwordEncoder.encode(dto.getNewPassword());
		user.setPasswordHash(newPassword);
		user = this.userLogonRepository.save(user);
		this.passwordResetRepo.delete(passwordReset);
	}
}
