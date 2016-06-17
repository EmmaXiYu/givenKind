package org.givenkind.validation;

import javax.inject.Inject;

import org.givenkind.dto.ProfileDTO;
import org.givenkind.dto.RegistrationDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mysql.jdbc.StringUtils;

public class NonprofitProfileValidator implements Validator {

	private static final String FIELDS_REQUIRED_CODE = "fields.required";
	private static final String THREE_CATEGORIES_CODE = "three.categories";
	private static final String ONE_CATEGORY_CODE = "one.category";
	private static final String FIELD_LENGTH_CODE = "field.length";
	private static final String IMPROPER_LENGTH_CODE = "improper.einlength";

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProfileDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		RegistrationDTO profileDTO = (RegistrationDTO) obj;
		if (StringUtils.isNullOrEmpty(profileDTO.getEmail())) {
			e.rejectValue("email", FIELDS_REQUIRED_CODE);
		}
		else{
			if(profileDTO.getEmail().length()>100) {
				e.rejectValue("email", FIELD_LENGTH_CODE);
			}
		}
		//Check Organization Name
		if(StringUtils.isNullOrEmpty(profileDTO.getMissionStatement())) {
			e.rejectValue("missionStatement", FIELDS_REQUIRED_CODE);
		}
		if(StringUtils.isNullOrEmpty(profileDTO.getOrganizationName())) {
			e.rejectValue("organizationName", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getOrganizationName().length()>200) {
			e.rejectValue("organizationName", FIELD_LENGTH_CODE);
		}
		
		//Check Website
		if(StringUtils.isNullOrEmpty(profileDTO.getWebsite())) {
			e.rejectValue("website", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getWebsite().length()>200) {
			e.rejectValue("website", FIELD_LENGTH_CODE);
		}
		
		//Check Address
		if(StringUtils.isNullOrEmpty(profileDTO.getAddress1())) {
			e.rejectValue("address1", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getAddress1().length()>100) {
			e.rejectValue("address1", FIELD_LENGTH_CODE);
		}
		if(profileDTO.getAddress2().length()>100) {
			e.rejectValue("address2", FIELD_LENGTH_CODE);
		}
		
		//Check City
		if(StringUtils.isNullOrEmpty(profileDTO.getCity())) {
			e.rejectValue("city", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getCity().length()>100) {
			e.rejectValue("city", FIELD_LENGTH_CODE);
		}
		
		//Check State
		if(StringUtils.isNullOrEmpty(profileDTO.getState())) {
			e.rejectValue("state", FIELDS_REQUIRED_CODE);
		}
		
		//Check Zip
		if(StringUtils.isNullOrEmpty(profileDTO.getZip())) {
			e.rejectValue("zip", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getZip().length()>12) {
			e.rejectValue("zip", FIELD_LENGTH_CODE);
		}
		
		//Check pickup distance
		if(profileDTO.getPickupService() && (profileDTO.getPickupDistance() == null)) {
			e.rejectValue("pickupDistance", FIELDS_REQUIRED_CODE);
		}
		
		//Check EIN 
		if(StringUtils.isNullOrEmpty(profileDTO.getEmployerIdentificationNumber())) {
			e.rejectValue("employerIdentificationNumber", FIELDS_REQUIRED_CODE);
		}
		if(!(profileDTO.getEmployerIdentificationNumber().length()==9)) {
			e.rejectValue("employerIdentificationNumber", IMPROPER_LENGTH_CODE);
		}
		
		//Check nonprofit categories
		if(profileDTO.getNonprofitCategories()!= null) {
			if(profileDTO.getNonprofitCategories().size()>3) {
				e.rejectValue("nonprofitCategories", THREE_CATEGORIES_CODE);
			}
		}
		else {
			e.rejectValue("nonprofitCategories", ONE_CATEGORY_CODE);
		}
		
		//Check Contact Person
		if(StringUtils.isNullOrEmpty(profileDTO.getContactPerson())) {
			e.rejectValue("contactPerson", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getContactPerson().length()>100) {
			e.rejectValue("contactPerson", FIELD_LENGTH_CODE);
		}
		
		//Check Contact Email
		if(StringUtils.isNullOrEmpty(profileDTO.getContactEmail())) {
			e.rejectValue("contactEmail", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getContactEmail().length()>100) {
			e.rejectValue("contactEmail", FIELD_LENGTH_CODE);
		}
		
		//Check contact phone
		if(StringUtils.isNullOrEmpty(profileDTO.getContactPhone())) {
			e.rejectValue("contactPhone", FIELDS_REQUIRED_CODE);
		}
		else if(profileDTO.getContactPhone().length()>20) {
			e.rejectValue("contactPhone", FIELD_LENGTH_CODE);
		}
		
	}

}