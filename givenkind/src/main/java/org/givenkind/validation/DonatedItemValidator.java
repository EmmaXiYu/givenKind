package org.givenkind.validation;

import javax.inject.Inject;

import org.givenkind.dto.DonorlistDTO;
import org.givenkind.service.DonorlistService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mysql.jdbc.StringUtils;

public class DonatedItemValidator implements Validator {

	private static final String FIELDS_REQUIRED_CODE = "fields.required";
	private static final String FIELDS_POSITIVE_CODE = "fields.positive";
	
	@Inject
	DonorlistService donorListService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return DonorlistDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		DonorlistDTO donorlistDTO = (DonorlistDTO) target;
		
		// Check the item name to make sure something is there
		if(StringUtils.isNullOrEmpty(donorlistDTO.getItemName())){
			e.rejectValue("itemName", FIELDS_REQUIRED_CODE);
		}
		
		// Check the quantity to make sure it exists and is not negative or zero
		if(donorlistDTO.getQuantity() == null){
			e.rejectValue("quantity", FIELDS_REQUIRED_CODE);
		}
		
		else if(donorlistDTO.getQuantity() <= 0){
			e.rejectValue("quantity", FIELDS_POSITIVE_CODE);
		}
		
		// Check to make sure that item has a category
		for(String c : donorlistDTO.getItemCategories()) {
			if(StringUtils.isNullOrEmpty(c)){
				e.rejectValue("itemCategory", FIELDS_REQUIRED_CODE);
			}
		}
		
		// Check to make sure condition is specified
		if(StringUtils.isNullOrEmpty(donorlistDTO.getCondition())) {
			e.rejectValue("condition", FIELDS_REQUIRED_CODE);
		}
		
		// Check to make sure fair market value exists and is not negative or zero		
		if(donorlistDTO.getFairMarketValue() <= 0.0){
			e.rejectValue("fairMarketValue", FIELDS_POSITIVE_CODE);
		}
		
		//Check to make sure that item has a description
		if(StringUtils.isNullOrEmpty(donorlistDTO.getItemName())){
			e.rejectValue("description", FIELDS_REQUIRED_CODE);
		}

	}

}
