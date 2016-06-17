package org.givenkind.controller;

import java.util.List;

import javax.inject.Inject;

import org.givenkind.dto.ProfileDTO;
import org.givenkind.model.UserLogon;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.ProfileService;
import org.givenkind.service.ReferenceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

abstract class AbstractProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractProfileController.class);
	
	@Inject
	protected ProfileService profileService;
	
	@Inject 
	private UserLogonRepository userLogonRepo;
	
	@Inject
	protected ReferenceDataService referenceDataService;
	
	@ModelAttribute("userId")
	protected Long getMyUserId() {
		String username = getMyUsername();
		UserLogon logon = userLogonRepo.findByLoginId(username);
		if(logon != null) {
			return logon.getId();
		} else {
			return -1L;
		}
	}
	
	protected ProfileDTO getAdminProfileData(String userId) {
		return profileService.prepareProfileData(userId);
	}
	
	protected ProfileDTO getNpProfileData(String userId) {
		return profileService.prepareProfileData(userId);
	}
	
	protected ProfileDTO getDonorProfileData(String userId) {
		return profileService.prepareDonorProfileData(userId);
	}
	
	protected String getMyUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			return userDetail.getUsername();
		}
		String principal = (String) auth.getPrincipal();
		logger.debug("User not authenticated, principal=" + principal);
		return principal;
	}
	
	@ModelAttribute("stateList")
	public List<String> populateStateList() {
		return referenceDataService.getStateList();
	}
	
	@ModelAttribute("nonprofitCategoryList")
	public List<String> populateNonprofitCategoryList() {
		return referenceDataService.getNonprofitCategoryList();
	}
	
}
