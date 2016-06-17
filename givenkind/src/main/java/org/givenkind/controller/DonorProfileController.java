package org.givenkind.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.givenkind.dto.ProfileDTO;
import org.givenkind.validation.DonorProfileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("profileDTO")
public class DonorProfileController extends AbstractProfileController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	
	
	@RequestMapping(value = "/donor/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("profileDTO") ProfileDTO profileDTO) {
		
		String username = getMyUsername();
		
		
		String userId = profileService.retrieveUserIdFromUsername(username);
		profileService.editDonorProfilePage(userId, profileDTO);
		return "redirect:/donor";
	}
	
}