package org.givenkind.service;

import java.util.List;

import org.givenkind.dto.ProfileDTO;
import org.givenkind.model.Profile;

public interface ProfileService {
	
	ProfileDTO prepareProfileData(String contactEmail);

	void editProfilePage(String contactEmail, ProfileDTO profileDTO);

	String retrieveUserIdFromUsername(String myUsername);
 
	List<Profile> prepareAdminPage();

	Long getProfileIdByUsername(String username);

	ProfileDTO prepareDonorProfileData(String alphanumId);

	void editDonorProfilePage(String alphanumId, ProfileDTO profileDTO);	
}