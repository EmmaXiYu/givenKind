package org.givenkind.service.impl;

import javax.inject.Inject;

import org.givenkind.dto.ForgotPasswordDTO;
import org.givenkind.dto.LoginDTO;
import org.givenkind.dto.PasswordResetAuthorizationDTO;
import org.givenkind.dto.PasswordResetDTO;
import org.givenkind.model.PasswordReset;
import org.givenkind.model.Profile;
import org.givenkind.model.UserLogon;
import org.givenkind.repository.PasswordResetRepository;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.EmailService;
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
	
	@Inject
	ProfileRepository profileRepository;
	
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
	
	@Autowired
	private EmailService emailService;
	
	@Override
	@Transactional
	public boolean forgotPassword(ForgotPasswordDTO dto,String httpURL ) throws NoSuchUserException {
		boolean status=false;
		PasswordResetAuthorizationDTO prDTO = new PasswordResetAuthorizationDTO();
		String emailAddress = dto.getEmail();
		//UserLogon userLogon = userLogonRepository.findByLoginId(emailAddress);
		Profile user = profileRepository.findByContactEmail(emailAddress);
		if(user != null) {
			log.info("user exist");
			UserLogon userLogon = userLogonRepository.findByProfile(user);
			PasswordReset pr = new PasswordReset();
			pr.setUser(userLogon);
			pr = passwordResetRepo.save(pr);			
			
			prDTO.setCreatedAt(pr.getCreatedAt());
			prDTO.setId(pr.getId());
			prDTO.setUniqueResetKey(pr.getUniqueResetKey());
			prDTO.setUserEmail(userLogon.getProfile().getContactEmail());
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("id", String.valueOf(pr.getId()));
			map.add("uniqueResetKey", pr.getUniqueResetKey());
			String passwordResetLink=UriComponentsBuilder.fromPath("resetPassword").queryParams(map).build().toUriString();
			prDTO.setPasswordResetLink(passwordResetLink);
			log.info(passwordResetLink);				
			
			emailService.forgotPasswordEmail(prDTO,httpURL);
			status =true;
		} else {
			log.info("no such user "+emailAddress);
			status =false;
		}
		return status;
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
