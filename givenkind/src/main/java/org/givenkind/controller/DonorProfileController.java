package org.givenkind.controller;

import java.util.Arrays;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.givenkind.dto.ProfileDTO;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.service.DonorlistService;
import org.givenkind.service.ProfileService;
import org.givenkind.validation.DonorProfileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("profileDTO")
public class DonorProfileController extends AbstractProfileController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Inject
	ProfileService profileService;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new DonorProfileValidator());
	}
	
	@RequestMapping(value = "/donor", method = RequestMethod.GET)
	public ModelAndView launchProfilePage() {
		ModelAndView mav = new ModelAndView();
		String username = getMyUsername();
		String userId = profileService.retrieveUserIdFromUsername(username);
		mav.addObject("profileDTO", getDonorProfileData(userId));
		mav.setViewName("/donorprofile");
		return mav;		
	}
	
	@RequestMapping(value = "/donor/edit", method = RequestMethod.GET)
	public ModelAndView launchProfileEditPage() {
		ModelAndView mav = new ModelAndView();
		String username = getMyUsername();
		String userId = profileService.retrieveUserIdFromUsername(username);
		mav.addObject("profileDTO", getDonorProfileData(userId));
		mav.addObject("stateList", populateStateList());
		mav.setViewName("/donorprofileedit");
		return mav;		
	}
	
	
	@RequestMapping(value = "/adminViewDonorProfile",method = RequestMethod.GET)
	public String adminViewDonorProfile(Model model, @RequestParam("donorProfileEmail") String email,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request) {
		if (userId == null) {
			userId = getMyUserId();
		}
		ProfileDTO profile = profileService.getDonorProfile(email);
		
		model.addAttribute("profile", profile);
		return "adminViewDonorProfile";
	}
	
	
	@RequestMapping(value = "/adminViewNPProfile",method = RequestMethod.GET)
	public String adminViewNPProfile(Model model, @RequestParam("npProfileEmail") String email,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request) {
		if (userId == null) {
			userId = getMyUserId();
		}
		ProfileDTO profile = profileService.getDonorProfile(email);
		
		model.addAttribute("profile", profile);
		return "adminViewNPProfile";
	}
	
	@RequestMapping(value = "/donor/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("profileDTO") ProfileDTO profileDTO) {
		
		String username = getMyUsername();
		
		
		String userId = profileService.retrieveUserIdFromUsername(username);
		profileService.editDonorProfilePage(userId, profileDTO);
		return "redirect:/donor";
	}
	
}