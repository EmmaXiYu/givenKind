package org.givenkind.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.common.ConversionUtil;
import org.givenkind.dto.ProfileDTO;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.NonProfitCategory;
import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.Profile;
import org.givenkind.model.State;
import org.givenkind.model.UserLogon;
import org.givenkind.repository.ItemCategoryRepository;
import org.givenkind.repository.NonProfitUserLogonRepository;
import org.givenkind.repository.NonprofitCategoryRepository;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.repository.StateRepository;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Inject
	private ProfileRepository profileRepository;
	
	@Inject
	private UserLogonRepository userRepository;
	
	@Inject
	NonprofitCategoryRepository nonprofitCategoryRepository;
	
	@Inject
	private StateRepository stateRepository;
	
	@Override
	public ProfileDTO prepareDonorProfileData(String alphanumId){
		ProfileDTO profileDTO = new ProfileDTO();
		
		Profile profile = profileRepository.findById(ConversionUtil.alphanumToNumeric(alphanumId));
		if(profile != null) {
			profileDTO.setWebsite(profile.getWebSiteUrl());
			profileDTO.setContactPerson(profile.getFullName());
			profileDTO.setContactEmail(profile.getContactEmail());
			profileDTO.setContactPhone(profile.getPhone1());
			profileDTO.setAddress1(profile.getAddressLine1());
			profileDTO.setAddress2(profile.getAddressLine2());
			profileDTO.setCity(profile.getCity());
			profileDTO.setState(profile.getState().getAbbreviation());
			profileDTO.setZip(profile.getZipCode());
		}
		return profileDTO;
	}
	
	@Override
	public void editDonorProfilePage(String alphanumId, ProfileDTO profileDTO) {
		
		Profile profile = profileRepository.findById(ConversionUtil.alphanumToNumeric(alphanumId));
		
		if (profile != null) {			
			profile.setAddressLine1(profileDTO.getAddress1());
			profile.setAddressLine2(profileDTO.getAddress2());
			profile.setCity(profileDTO.getCity());		
			profile.setState(stateRepository.findByAbbreviation(profileDTO.getState()));
			profile.setZipCode(profileDTO.getZip());
			profile.setFullName(profileDTO.getContactPerson());
			profile.setContactEmail(profileDTO.getContactEmail());
			profile.setPhone1(profileDTO.getContactPhone());
			
			profileRepository.save(profile);
		}
		
	}
	
	@Override
	public ProfileDTO prepareProfileData(String alphanumId) {
		ProfileDTO profileDTO = new ProfileDTO();
		
		
		Profile profile = profileRepository.findById(ConversionUtil.alphanumToNumeric(alphanumId));
		
		if (profile != null) {
			profileDTO.setOrganizationName(profile.getOrganizationName());
			profileDTO.setMissionStatement(profile.getMissionStatement());
			profileDTO.setWebsite(profile.getWebSiteUrl());
			profileDTO.setAddress1(profile.getAddressLine1());
			profileDTO.setAddress2(profile.getAddressLine2());
			profileDTO.setCity(profile.getCity());
			profileDTO.setState(profile.getState().getAbbreviation());
			List<String> categories = new ArrayList<String>();
			if(profile.getCategories() != null) {
				for(NonProfitCategory category : profile.getCategories()) {
					categories.add(category.getName());
				}
			}
			profileDTO.setNonprofitCategories(categories);
			profileDTO.setZip(profile.getZipCode());
			profileDTO.setPickupService(profile.getIsPickupServiceAvailable() ? "Yes" : "No");
			profileDTO.setPickupDistance(profile.getTravelDistance());
			profileDTO.setEmployerIdentificationNumber(profile.getEin());
			profileDTO.setContactPerson(profile.getFullName());
			profileDTO.setContactEmail(profile.getContactEmail());
			profileDTO.setContactPhone(profile.getPhone1());
		}
		
		return profileDTO;
	}
	

	@Override
	public void editProfilePage(String alphanumId, ProfileDTO profileDTO) {
		
		Profile profile = profileRepository.findById(ConversionUtil.alphanumToNumeric(alphanumId));
		
		if (profile != null) {
			profile.setOrganizationName(profileDTO.getOrganizationName());
			profile.setMissionStatement(profileDTO.getMissionStatement());
			profile.setWebSiteUrl(profileDTO.getWebsite());
			profile.setAddressLine1(profileDTO.getAddress1());
			profile.setAddressLine2(profileDTO.getAddress2());
			profile.setCity(profileDTO.getCity());		
			profile.setState(stateRepository.findByAbbreviation(profileDTO.getState()));
			List<NonProfitCategory> itemCategories = new ArrayList<NonProfitCategory>();
			List<String> itemCategoriesStr = profileDTO.getNonprofitCategories();
			for(String item : itemCategoriesStr){
				itemCategories.add(nonprofitCategoryRepository.findByName(item));
			}
			profile.setCategories(itemCategories);
			profile.setZipCode(profileDTO.getZip());
			profile.setIsPickupServiceAvailable(profileDTO.getPickupService().equalsIgnoreCase("yes"));
			profile.setTravelDistance(profileDTO.getPickupDistance());
			profile.setEin(profileDTO.getEmployerIdentificationNumber());
			profile.setFullName(profileDTO.getContactPerson());
			profile.setContactEmail(profileDTO.getContactEmail());
			profile.setPhone1(profileDTO.getContactPhone());
			profileRepository.save(profile);
			
		}
		
	}

	@Override
	public Long getProfileIdByUsername(String username) {
		UserLogon user = userRepository.findByLoginId(username);
		if(user != null) {
			return user.getProfile().getId();
		} else {
			return null;
		}
	}
	
	@Override
	public String retrieveUserIdFromUsername(String username) {
		UserLogon user = userRepository.findByLoginId(username);
		
		if (user != null) {
			return ConversionUtil.numericToAlphanum(user.getId());
		} else {
			return "";
		}
	}
	
	@Override
	public List<Profile> prepareAdminPage() {
		List<Profile> profileList = profileRepository.findAll();
		return profileList;
	}

	@Override
	public ProfileDTO getDonorProfile(String email) {
		ProfileDTO profileDTO = new ProfileDTO();
		Profile profile =profileRepository.findByContactEmail(email);
		
		if(profile != null) {
			profileDTO.setWebsite(profile.getWebSiteUrl());
			profileDTO.setContactPerson(profile.getFullName());
			profileDTO.setContactEmail(profile.getContactEmail());
			profileDTO.setContactPhone(profile.getPhone1());
			profileDTO.setAddress1(profile.getAddressLine1());
			profileDTO.setAddress2(profile.getAddressLine2());
			profileDTO.setCity(profile.getCity());
			profileDTO.setState(profile.getState().getAbbreviation());
			profileDTO.setZip(profile.getZipCode());
		}
		return profileDTO;
		}

	@Override
	public ProfileDTO getNPProfile(String email) {
		ProfileDTO profileDTO = new ProfileDTO();
		Profile profile =profileRepository.findByContactEmail(email);
		if (profile != null) {
			profileDTO.setOrganizationName(profile.getOrganizationName());
			profileDTO.setMissionStatement(profile.getMissionStatement());
			profileDTO.setWebsite(profile.getWebSiteUrl());
			profileDTO.setAddress1(profile.getAddressLine1());
			profileDTO.setAddress2(profile.getAddressLine2());
			profileDTO.setCity(profile.getCity());
			profileDTO.setState(profile.getState().getAbbreviation());
			List<String> categories = new ArrayList<String>();
			if(profile.getCategories() != null) {
				for(NonProfitCategory category : profile.getCategories()) {
					categories.add(category.getName());
				}
			}
			profileDTO.setNonprofitCategories(categories);
			profileDTO.setZip(profile.getZipCode());
			profileDTO.setPickupService(profile.getIsPickupServiceAvailable() ? "Yes" : "No");
			profileDTO.setPickupDistance(profile.getTravelDistance());
			profileDTO.setEmployerIdentificationNumber(profile.getEin());
			profileDTO.setContactPerson(profile.getFullName());
			profileDTO.setContactEmail(profile.getContactEmail());
			profileDTO.setContactPhone(profile.getPhone1());
		}
		
		
		return profileDTO;
	}

	@Override
	public List<List<ProfileDTO>> getAllProfile() {
		List<List<ProfileDTO>>  ll= new ArrayList<>();
		List<ProfileDTO> nonProfitDTO = new ArrayList<>();
		List<ProfileDTO> donorProfitDTO = new ArrayList<>();
		
		
		List<Profile> list = profileRepository.findAll();
		
		for(Profile profile : list){
			if(profile.getUser().getRole().getId()==2){
				//none-profit
				ProfileDTO profileDTO = new ProfileDTO();
				if (profile != null) {
					if(profile.getOrganizationName()!=null)
					profileDTO.setOrganizationName(profile.getOrganizationName());
					if(profile.getMissionStatement()!=null)
					profileDTO.setMissionStatement(profile.getMissionStatement());
					if(profile.getWebSiteUrl()!=null)
					profileDTO.setWebsite(profile.getWebSiteUrl());
					if(profile.getAddressLine1()!=null)
					profileDTO.setAddress1(profile.getAddressLine1());
					if(profile.getAddressLine2()!=null)
					profileDTO.setAddress2(profile.getAddressLine2());
					if(profile.getCity()!=null)
					profileDTO.setCity(profile.getCity());
					if(profile.getState()!=null)
					profileDTO.setState(profile.getState().getAbbreviation());
					List<String> categories = new ArrayList<String>();
					List<NonProfitCategory> cate =  profile.getCategories();
					if(cate != null) {
						for(NonProfitCategory category : cate) {
						categories.add(category.getName());
						}
					}
			
					profileDTO.setNonprofitCategories(categories);
					if(profile.getZipCode()!=null)
					profileDTO.setZip(profile.getZipCode());
					if(profile.getIsPickupServiceAvailable()!=null)
				profileDTO.setPickupService(profile.getIsPickupServiceAvailable() ? "Yes" : "No");
					if(profile.getTravelDistance()!=null)
					profileDTO.setPickupDistance(profile.getTravelDistance());
					if(profile.getEin()!=null)
					profileDTO.setEmployerIdentificationNumber(profile.getEin());
					if(profile.getFullName()!=null)
					profileDTO.setContactPerson(profile.getFullName());
					if(profile.getContactEmail()!=null)
					profileDTO.setContactEmail(profile.getContactEmail());
					if(profile.getPhone1()!=null)
					profileDTO.setContactPhone(profile.getPhone1());
					
				}
				
				nonProfitDTO.add(profileDTO);
				
			}
			else if(profile.getUser().getRole().getId()==3){
				// donor
				ProfileDTO profileDTO = new ProfileDTO();
				if(profile.getWebSiteUrl()!=null)
				profileDTO.setWebsite(profile.getWebSiteUrl());
				if(profile.getFullName()!=null)
				profileDTO.setContactPerson(profile.getFullName());
				if(profile.getContactEmail()!=null)
				profileDTO.setContactEmail(profile.getContactEmail());
				if(profile.getPhone1()!=null)
				profileDTO.setContactPhone(profile.getPhone1());
				if(profile.getAddressLine1()!=null)
				profileDTO.setAddress1(profile.getAddressLine1());
				if(profile.getAddressLine2()!=null)
				profileDTO.setAddress2(profile.getAddressLine2());
				if(profile.getCity()!=null)
				profileDTO.setCity(profile.getCity());
				if(profile.getState()!=null)
				profileDTO.setState(profile.getState().getAbbreviation());
				if(profile.getZipCode()!=null)
				profileDTO.setZip(profile.getZipCode());
				donorProfitDTO.add(profileDTO);
			}
			
			
		}
		ll.add(nonProfitDTO);
		ll.add(donorProfitDTO);
		return ll;
	}
}
