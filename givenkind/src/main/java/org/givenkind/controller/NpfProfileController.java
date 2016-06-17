package org.givenkind.controller;

import org.givenkind.dto.ProfileDTO;
import org.givenkind.validation.DonorProfileValidator;
import org.givenkind.validation.NonprofitProfileValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("profileDTO")
public class NpfProfileController extends AbstractProfileController {
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new NonprofitProfileValidator());
	}
	
	@RequestMapping(value = "/nonprofit", method = RequestMethod.GET)
	public String launchMyProfilePage() {
		String username = getMyUsername();
		String userId = profileService.retrieveUserIdFromUsername(username);
		return "redirect:/nonprofit/" + userId;		
	}
	
	@RequestMapping(value = "/nonprofit/{userId}", method = RequestMethod.GET)
	public ModelAndView launchProfilePage(@PathVariable String userId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("profileDTO", getNpProfileData(userId));
		mav.setViewName("/npfprofile");
		return mav;		
	}

	@RequestMapping(value = "/nonprofit/edit", method = RequestMethod.GET)
	public ModelAndView launchProfileEditPage() {
		ModelAndView mav = new ModelAndView();
		String username = getMyUsername();
		String userId = profileService.retrieveUserIdFromUsername(username);
		mav.addObject("profileDTO", getNpProfileData(userId));
		mav.addObject("stateList", populateStateList());
		mav.addObject("nonprofitCategoryList",populateNonprofitCategoryList());
		mav.setViewName("/npfprofileedit");
		return mav;		
	}
	
	@RequestMapping(value = "/nonprofit/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("profileDTO") ProfileDTO profileDTO) {
		String username = getMyUsername();
		String userId = profileService.retrieveUserIdFromUsername(username);
		profileService.editProfilePage(userId, profileDTO);
		return "redirect:/nonprofit";		
	}

}
