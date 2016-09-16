package org.givenkind.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.givenkind.common.UserRoleStrings;
import org.givenkind.dto.DonorRegistrationDTO;
import org.givenkind.dto.RegistrationDTO;
import org.givenkind.model.DonorUserLogon;
import org.givenkind.model.NonProfitCategory;
import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.Profile;
import org.givenkind.model.State;
import org.givenkind.model.UserLogon;
import org.givenkind.model.UserRole;
import org.givenkind.repository.NonprofitCategoryRepository;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.repository.StateRepository;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.repository.UserRoleRepository;
import org.givenkind.service.EmailService;
import org.givenkind.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String ADMIN_ROLE = UserRoleStrings.ROLE_ADMIN;
	private static final String NONPROFIT_ROLE = UserRoleStrings.ROLE_NONPROFIT;
	private static final String DONOR_ROLE = UserRoleStrings.ROLE_DONOR;
	private static final String VOLUNTEER_ROLE = UserRoleStrings.ROLE_VOLUNTEER;
	
	@Inject 
	private PasswordEncoder passwordEncoder;
	
	
	@Inject
	ProfileRepository profileRepository;
	
	@Inject
	UserLogonRepository userLogonRepository;
	
	@Inject
	StateRepository stateRepository;
	
	@Inject
	NonprofitCategoryRepository nonprofitCategoryRepository;
		
	@Inject
	UserRoleRepository userRoleRepository;
	
	@Inject
	EmailService emailService;
	
	@Override
	public RegistrationDTO prepareRegistrationPage() {
		RegistrationDTO registrationDTO = new RegistrationDTO();
		return registrationDTO;
	}	

	@Override
	@Transactional
	public Long registerUser(RegistrationDTO registrationDTO) {
		boolean userExists = userExists(registrationDTO.getEmail());
		log.info("user "+registrationDTO.getEmail()+" already exists? "+userExists);
		if(userExists){
			return -1L;
		}
		
		UserRole userRole = userRoleRepository.findByName(NONPROFIT_ROLE);
		
		log.info("converting from dto");
		NonProfitUserLogon userLogon = nonprofitRegistrationDtoToUserLogon(registrationDTO);
		userLogon.setRole(userRole);
		userLogon.setProfile(null);
		log.info("saving user to repositoy");
		userLogon = userLogonRepository.save(userLogon);
		log.info("saved user to repository");
		Profile profile = registrationDtoToProfile(registrationDTO);
		profile.setModifiedBy(registrationDTO.getEmail());
		profile.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));
		profile.setUser(userLogon);
		
		/*List<NonProfitCategory> nonProfitCategories = new ArrayList<NonProfitCategory>();
		for(String s : registrationDTO.getNonprofitCategories()) {
			NonProfitCategory category = this.nonprofitCategoryRepository.findByName(s);
			if(category != null) {
				nonProfitCategories.add(category);
			}
		}
		*/
		
		log.info("saving profile to repository");
		profile = profileRepository.save(profile);
		
		userLogon.setProfile(profile);
		//profile.setCategories(nonProfitCategories);
		userLogon = userLogonRepository.save(userLogon);
		
		log.info("saved profile to repository");
		
		
		log.info("retrieved user role");
		
		//TODO: Move this to after the wishlist is submitted
		//emailService.adminRegistrationEmail("INSERT THE PROFILE URL HERE");
		emailService.ngoRegistrationEmail(registrationDTO.getEmail());
		// TODO: restore usage of email service here
		return profile.getId();	
	}
	
	private Profile registrationDtoToProfile(RegistrationDTO registrationDTO){
		Profile profile = new Profile();
		profile.setAddressLine1(registrationDTO.getAddress1());
		profile.setAddressLine2(registrationDTO.getAddress2());
		profile.setCity(registrationDTO.getCity());
		profile.setContactEmail(registrationDTO.getContactEmail());
		List<NonProfitCategory> itemCategories = new ArrayList<NonProfitCategory>();
		List<String> itemCategoriesStr = registrationDTO.getNonprofitCategories();
		for(String item : itemCategoriesStr){
			itemCategories.add(nonprofitCategoryRepository.findByName(item));
		}
		profile.setCategories(itemCategories);
		//profile.setCountry(registrationDTO.getCountry());
		profile.setEIN(registrationDTO.getEmployerIdentificationNumber());
		profile.setFullName(registrationDTO.getContactPerson());
		profile.setIsPickupServiceAvailable(registrationDTO.getPickupService());
		// TODO: add default profile approved
		profile.setApprovalStatus("Pending Approval");
		//profile.setLogoLocation(registrationDTO.getLogoLocation());
		profile.setMissionStatement(registrationDTO.getMissionStatement());
		profile.setModifiedBy(registrationDTO.getEmail());
		profile.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));
		profile.setOrganizationName(registrationDTO.getOrganizationName());
		profile.setPhone1(registrationDTO.getContactPhone());
		State state = stateRepository.findByAbbreviation(registrationDTO.getState());
		profile.setState(state);
		profile.setTravelDistance(registrationDTO.getPickupDistance());
		profile.setWebSiteUrl(registrationDTO.getWebsite());
		profile.setZipCode(registrationDTO.getZip());
		return profile;
	}
	
	private NonProfitUserLogon nonprofitRegistrationDtoToUserLogon(RegistrationDTO registrationDTO){
		NonProfitUserLogon userLogon = new NonProfitUserLogon();
		userLogon.setLoginId(registrationDTO.getEmail());

		String password = passwordEncoder.encode(registrationDTO.getPassword());
		userLogon.setPasswordHash(password);
		userLogon.setModifiedBy(registrationDTO.getEmail());
		userLogon.setLastLoggedInDate(new Date(Calendar.getInstance().getTimeInMillis()));
		userLogon.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
		userLogon.setIsActive(true);
		return userLogon;
	}
	
	@Transactional
	public Long registerDonor(DonorRegistrationDTO donorRegistrationDTO){
		boolean userExists = userExists(donorRegistrationDTO.getEmail());
		if(userExists){
			return -1L;
		}
		
		UserRole userRole = userRoleRepository.findByName(DONOR_ROLE);
		
		Profile profile = new Profile();
		profile.setUser(null);
		profile.setFullName(donorRegistrationDTO.getName());
		profile.setAddressLine1(donorRegistrationDTO.getAddress1());
		profile.setAddressLine2(donorRegistrationDTO.getAddress2());
		profile.setCity(donorRegistrationDTO.getCity());
		State state = stateRepository.findByAbbreviation(donorRegistrationDTO.getState());
		profile.setApprovalStatus("Approved");
		profile.setState(state);
		profile.setZipCode(donorRegistrationDTO.getZip());
		profile.setModifiedDate(new Date(Calendar.getInstance().getTimeInMillis()));
		profile.setModifiedBy(donorRegistrationDTO.getEmail());
		profile = profileRepository.save(profile);
		
		DonorUserLogon userLogon = new DonorUserLogon();
		userLogon.setRole(userRole);
		userLogon.setLoginId(donorRegistrationDTO.getEmail());
		userLogon.setProfile(profile);
		String password = passwordEncoder.encode(donorRegistrationDTO.getPassword());
		userLogon.setPasswordHash(password);
		userLogon.setModifiedBy(donorRegistrationDTO.getEmail());
		userLogon.setLastLoggedInDate(new Date(Calendar.getInstance().getTimeInMillis()));
		userLogon.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
		userLogon.setIsActive(true);
		// TODO: save to donor specific user logon repo
		userLogon = userLogonRepository.save(userLogon);
		
		profile.setUser(userLogon);
		profile = profileRepository.save(profile);
		
		//emailService.donorRegistrationEmail(donorRegistrationDTO.getEmail());
		return profile.getId();
	}
	
	public DonorRegistrationDTO prepareDonorRegistrationPage(){
		DonorRegistrationDTO donorRegistrationDTO = new DonorRegistrationDTO();
		return donorRegistrationDTO;
	}
	
	private boolean userExists(String email) {
		boolean userExists = false;
		UserLogon user = userLogonRepository.findByLoginId(email);
		if(user != null)
		{
			userExists = true;
		}
		return userExists;
	}
}
