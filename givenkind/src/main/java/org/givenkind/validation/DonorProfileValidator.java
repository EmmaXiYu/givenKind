package org.givenkind.validation;

import org.givenkind.dto.ProfileDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mysql.jdbc.StringUtils;

public class DonorProfileValidator implements Validator {

	private static final String FIELDS_REQUIRED_CODE = "fields.required";
	private static final String PASSWORD_DOES_NOT_MATCH_CODE = "password.mismatch";
	private static final String PASSWORD_LENGTH_CODE = "password.length";
	private static final String FIELD_LENGTH_CODE = "field.length";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProfileDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ProfileDTO profileDTO = (ProfileDTO) obj;
		if (StringUtils.isNullOrEmpty(profileDTO.getEmail())) {
			e.rejectValue("email", FIELDS_REQUIRED_CODE);
		}
		else{
			if(profileDTO.getEmail().length()>100) {
				e.rejectValue("email", FIELD_LENGTH_CODE);
			}
		}
		if(StringUtils.isNullOrEmpty(profileDTO.getAddress1())) {
			e.rejectValue("address1", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getAddress1().length()>100) {
			e.rejectValue("address1", FIELD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(profileDTO.getCity())) {
			e.rejectValue("city", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getCity().length()>100) {
			e.rejectValue("city", FIELD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(profileDTO.getState())) {
			e.rejectValue("state", FIELDS_REQUIRED_CODE);
		}
		if(StringUtils.isNullOrEmpty(profileDTO.getZip())) {
			e.rejectValue("zip", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getZip().length()>12) {
			e.rejectValue("zip", FIELD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(profileDTO.getContactPerson())) {
			e.rejectValue("name", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getContactPerson().length()>100) {
			e.rejectValue("name", FIELD_LENGTH_CODE);
		}
		if(StringUtils.isNullOrEmpty(profileDTO.getContactPhone())) {
			e.rejectValue("contactPhone", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getContactPhone().length()>10) {
			e.rejectValue("contactPhone", FIELD_LENGTH_CODE);
		}	
	}
}
