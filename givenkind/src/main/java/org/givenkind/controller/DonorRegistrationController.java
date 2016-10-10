package org.givenkind.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.givenkind.dto.DonorRegistrationDTO;
import org.givenkind.service.ReferenceDataService;
import org.givenkind.service.RegistrationService;
import org.givenkind.validation.DonorRegistrationValidator;
import org.givenkind.validation.captchaValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registerDonor")
@SessionAttributes("donorRegistrationDTO")
public class DonorRegistrationController {
	
	@Inject
	RegistrationService registrationService;
	
	@Inject
	ReferenceDataService referenceDataService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new DonorRegistrationValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView launchRegistrationPage() {
		ModelAndView mav = new ModelAndView();
		DonorRegistrationDTO donorRegistrationDTO = registrationService.prepareDonorRegistrationPage();
		mav.addObject(donorRegistrationDTO);
		mav.setViewName("donorregister");
		return mav;		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("donorRegistrationDTO") DonorRegistrationDTO donorRegistrationDTO,
			BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return "donorregister";
		}
		
		// Processing captcha status
		try{
			String captchaResponse = request.getParameter("g-recaptcha-response");
			boolean isValid = captchaValidator.verify(captchaResponse);
				
			if(isValid){
		
				Long profileId = registrationService.registerDonor(donorRegistrationDTO);
				if(profileId == -1L)
				{
					return "redirect:alreadyRegistered";
				}
				status.setComplete();
				model.asMap().clear();
				return "loginRegisterSuccessful";
			}
			else{
				return "donorregister";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "register";
		}
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
