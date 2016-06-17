package org.givenkind.validation;

import javax.inject.Inject;

import org.givenkind.dto.DonorRegistrationDTO;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.LoginService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mysql.jdbc.StringUtils;

public class DonorRegistrationValidator implements Validator {

	private static final String FIELDS_REQUIRED_CODE = "fields.required";
	private static final String PASSWORD_DOES_NOT_MATCH_CODE = "password.mismatch";
	private static final String INVALID_EMAIL_CODE = "invalid.email";
	private static final String PASSWORD_LENGTH_CODE = "password.length";
	private static final String FIELD_LENGTH_CODE = "field.length";
	private static final String EMAIL_USED_CODE = "email.used";
	private static final String ZIP_FORMAT_CODE = "invalid.zip";
	
	private ZipValidator zipValidator;
	
	@Inject
	private UserLogonRepository userLogonRepository;
	
	@Inject
	private LoginService loginService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return DonorRegistrationDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		DonorRegistrationDTO donorRegistrationDTO = (DonorRegistrationDTO) obj;
		if (StringUtils.isNullOrEmpty(donorRegistrationDTO.getEmail())) {
			e.rejectValue("email", FIELDS_REQUIRED_CODE);
		}
		else{
			if(donorRegistrationDTO.getEmail().length()>100) {
				e.rejectValue("email", FIELD_LENGTH_CODE);
			}
			/*else if(loginService.findUserByEmail(donorRegistrationDTO.getEmail())!=null){
				e.rejectValue("email", EMAIL_USED_CODE);
			}*/
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getPassword())) {
			e.rejectValue("password", FIELDS_REQUIRED_CODE);
		}
		else if(donorRegistrationDTO.getPassword().length() < 8 || 
				donorRegistrationDTO.getPassword().length() > 20) {
			e.rejectValue("password", PASSWORD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getConfirmPassword())) {
			e.rejectValue("confirmPassword", FIELDS_REQUIRED_CODE);
		}
		if(!donorRegistrationDTO.getPassword().equals(donorRegistrationDTO.getConfirmPassword())){
			e.rejectValue("confirmPassword", PASSWORD_DOES_NOT_MATCH_CODE);
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getAddress1())) {
			e.rejectValue("address1", FIELDS_REQUIRED_CODE);
		}
		else if(donorRegistrationDTO.getAddress1().length()>100) {
			e.rejectValue("address1", FIELD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getCity())) {
			e.rejectValue("city", FIELDS_REQUIRED_CODE);
		}
		else if(donorRegistrationDTO.getCity().length()>100) {
			e.rejectValue("city", FIELD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getState())) {
			e.rejectValue("state", FIELDS_REQUIRED_CODE);
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getZip())) {
			e.rejectValue("zip", FIELDS_REQUIRED_CODE);
		}
		
		ZipValidator test = new ZipValidator();
		if(!test.validate(donorRegistrationDTO.getZip())){
			e.rejectValue("zip", ZIP_FORMAT_CODE);
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getName())) {
			e.rejectValue("name", FIELDS_REQUIRED_CODE);
		}
		else if(donorRegistrationDTO.getName().length()>100) {
			e.rejectValue("name", FIELD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(donorRegistrationDTO.getContactPhone())) {
			e.rejectValue("contactPhone", FIELDS_REQUIRED_CODE);
		}
		else if(donorRegistrationDTO.getContactPhone().length()>20) {
			e.rejectValue("contactPhone", FIELD_LENGTH_CODE);
		}	
	}
}
