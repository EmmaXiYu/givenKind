package org.givenkind.controller;

import java.util.List;

import javax.inject.Inject;

import org.givenkind.model.Profile;
import org.givenkind.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("profileDTO")
@RequestMapping("/admin/view")
public class AdminController extends AbstractProfileController{
	
	@Inject
	ProfileService profileService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String launchAdminPage(Model model) {
		List<Profile> profileList = profileService.prepareAdminPage();
		model.addAttribute("profileList", profileList);
		model.addAttribute("profileList");
		return "adminview";			
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET, params = {"userName"})
	public ModelAndView launchDetailPage(@RequestParam String userName){
		ModelAndView mav = new ModelAndView();
		String userId = profileService.retrieveUserIdFromUsername(userName);
		mav.addObject("profileDTO", getAdminProfileData(userId));
		mav.setViewName("/npfprofile");
		return mav;
	}
	
}