package org.givenkind.validation;

import javax.inject.Inject;

import org.givenkind.dto.RegistrationDTO;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.LoginService;
import org.givenkind.validation.captchaValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mysql.jdbc.StringUtils;

public class RegistrationValidator implements Validator {

	private static final String FIELDS_REQUIRED_CODE = "fields.required";
	private static final String PASSWORD_DOES_NOT_MATCH_CODE = "password.mismatch";
	private static final String THREE_CATEGORIES_CODE = "three.categories";
	private static final String INVALID_EMAIL_CODE = "invalid.email";
	private static final String PASSWORD_LENGTH_CODE = "password.length";
	private static final String ONE_CATEGORY_CODE = "one.category";
	private static final String FIELD_LENGTH_CODE = "field.length";
	private static final String EMAIL_USED_CODE = "email.used";
	private static final String IMPROPER_LENGTH_CODE = "improper.einlength";
	private static final String ZIP_FORMAT_CODE = "invalid.zip";
	
	private EmailValidator emailValidator;
	private ZipValidator zipValidator;
	
	@Inject
	LoginService loginService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		RegistrationDTO registrationDTO = (RegistrationDTO) obj;
		
		//Check email
		
		if (StringUtils.isNullOrEmpty(registrationDTO.getEmail())) {
			e.rejectValue("email", FIELDS_REQUIRED_CODE);
		}
		else{
			if(registrationDTO.getEmail().length()>100) {
				e.rejectValue("email", FIELD_LENGTH_CODE);
			}
			/*else if(loginService.findUserByEmail(registrationDTO.getEmail())!=null){
				e.rejectValue("email", EMAIL_USED_CODE);
			}*/
		}
		
		//Check Password
		if(StringUtils.isNullOrEmpty(registrationDTO.getPassword())) {
			e.rejectValue("password", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getPassword().length() < 8 || 
				registrationDTO.getPassword().length() > 20) {
			e.rejectValue("password", PASSWORD_LENGTH_CODE);
		}
		
		//Check password confirmation
		if(StringUtils.isNullOrEmpty(registrationDTO.getConfirmPassword())) {
			e.rejectValue("confirmPassword", FIELDS_REQUIRED_CODE);
		}
		if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
			e.rejectValue("confirmPassword", PASSWORD_DOES_NOT_MATCH_CODE);
		}
		
		//Check mission statement
		if(StringUtils.isNullOrEmpty(registrationDTO.getMissionStatement())) {
			e.rejectValue("missionStatement", FIELDS_REQUIRED_CODE);
		}
		
		//check organization name
		if(StringUtils.isNullOrEmpty(registrationDTO.getOrganizationName())) {
			e.rejectValue("organizationName", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getOrganizationName().length()>200) {
			e.rejectValue("organizationName", FIELD_LENGTH_CODE);
		}
		
		//check website
		if(StringUtils.isNullOrEmpty(registrationDTO.getWebsite())) {
			e.rejectValue("website", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getWebsite().length()>200) {
			e.rejectValue("website", FIELD_LENGTH_CODE);
		}
		
		//check address line 1
		if(StringUtils.isNullOrEmpty(registrationDTO.getAddress1())) {
			e.rejectValue("address1", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getAddress1().length()>100) {
			e.rejectValue("address1", FIELD_LENGTH_CODE);
		}
		
		//check address line 2
		if(registrationDTO.getAddress2().length()>100) {
			e.rejectValue("address2", FIELD_LENGTH_CODE);
		}
		
		//check city
		if(StringUtils.isNullOrEmpty(registrationDTO.getCity())) {
			e.rejectValue("city", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getCity().length()>100) {
			e.rejectValue("city", FIELD_LENGTH_CODE);
		}
		
		//check state
		if(StringUtils.isNullOrEmpty(registrationDTO.getState())) {
			e.rejectValue("state", FIELDS_REQUIRED_CODE);
		}
		
		//check zip
		if(StringUtils.isNullOrEmpty(registrationDTO.getZip())) {
			e.rejectValue("zip", FIELDS_REQUIRED_CODE);
		}

		ZipValidator test = new ZipValidator();
		if(!test.validate(registrationDTO.getZip())){
			e.rejectValue("zip", ZIP_FORMAT_CODE);
		}
		
		
		//check pickup distance
		if(registrationDTO.getPickupService() && (registrationDTO.getPickupDistance() == null)) {
			e.rejectValue("pickupDistance", FIELDS_REQUIRED_CODE);
		}
		
		//check EIN
		if(StringUtils.isNullOrEmpty(registrationDTO.getEmployerIdentificationNumber())) {
			e.rejectValue("employerIdentificationNumber", FIELDS_REQUIRED_CODE);
		}
		else if(!(registrationDTO.getEmployerIdentificationNumber().length()==9)) {
			e.rejectValue("employerIdentificationNumber", IMPROPER_LENGTH_CODE);
		}
		
		//check nonprofit categories
		if(registrationDTO.getNonprofitCategories()!= null) {
			if(registrationDTO.getNonprofitCategories().size()>3) {
				e.rejectValue("nonprofitCategories", THREE_CATEGORIES_CODE);
			}
		}
		else {
			e.rejectValue("nonprofitCategories", ONE_CATEGORY_CODE);
		}
		
		//check contact person
		if(StringUtils.isNullOrEmpty(registrationDTO.getContactPerson())) {
			e.rejectValue("contactPerson", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getContactPerson().length()>100) {
			e.rejectValue("contactPerson", FIELD_LENGTH_CODE);
		}
		
		//check contact email
		if(StringUtils.isNullOrEmpty(registrationDTO.getContactEmail())) {
			e.rejectValue("contactEmail", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getContactEmail().length()>100) {
			e.rejectValue("contactEmail", FIELD_LENGTH_CODE);
		}
		
		//check contact phone
		if(StringUtils.isNullOrEmpty(registrationDTO.getContactPhone())) {
			e.rejectValue("contactPhone", FIELDS_REQUIRED_CODE);
		}
		else if(registrationDTO.getContactPhone().length()>20) {
			e.rejectValue("contactPhone", FIELD_LENGTH_CODE);
		}
	}

}