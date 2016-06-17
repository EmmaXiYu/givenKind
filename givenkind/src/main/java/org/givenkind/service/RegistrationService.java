package org.givenkind.service;

import org.givenkind.dto.DonorRegistrationDTO;
import org.givenkind.dto.RegistrationDTO;

public interface RegistrationService {

	RegistrationDTO prepareRegistrationPage();
	
	DonorRegistrationDTO prepareDonorRegistrationPage();
	
	Long registerUser(RegistrationDTO registrationDTO);
	
	Long registerDonor(DonorRegistrationDTO donorRegistrationDTO);
	
}