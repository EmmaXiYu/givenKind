package org.givenkind.controller;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.givenkind.dto.RegistrationDTO;
import org.givenkind.model.UserLogon;
import org.givenkind.service.LoginService;
import org.givenkind.service.ReferenceDataService;
import org.givenkind.service.RegistrationService;
import org.givenkind.validation.captchaValidator;
import org.givenkind.validation.RegistrationValidator;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("registrationDTO")
public class RegistrationController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	RegistrationService registrationService;

	@Inject
	ReferenceDataService referenceDataService;

	@Inject
	LoginService loginService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new RegistrationValidator());
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView launchRegistrationPage() {
		ModelAndView mav = new ModelAndView();
		RegistrationDTO registrationDTO = registrationService.prepareRegistrationPage();
		mav.addObject(registrationDTO);
		mav.setViewName("register");
		return mav;
	}

	@RequestMapping(value = "/alreadyRegisteredUser", method = RequestMethod.GET)
	public ModelAndView launchThanksPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alreadyRegisteredUser");
		return mav;
	}
	@RequestMapping(value = "/alreadyRegisteredEIN", method = RequestMethod.GET)
	public ModelAndView launchPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alreadyRegisteredEIN");
		return mav;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
			BindingResult result, SessionStatus status, Model model, HttpServletRequest request) {
		
		log.info("invoked register");
		if (result.hasErrors()) {
			log.error("errors present "+Arrays.toString(result.getAllErrors().toArray()));
			return "register";
		}
		
		// Processing captcha status
		try{
			String captchaResponse = request.getParameter("g-recaptcha-response");

			boolean isValid = captchaValidator.verify(captchaResponse);			
		
			registrationDTO.setValidCaptcha(isValid);			
		
			if(isValid){
				// process DTO and go to profile?
				Long profileId = registrationService.registerUser(registrationDTO);
				if(profileId == -1L){
					return "redirect:alreadyRegisteredUser";
				}
				if(profileId == -2L){
					return "redirect:alreadyRegisteredEIN";
				}
				status.setComplete();
				model.asMap().clear();
		
				return "loginRegisterSuccessful";		
			}
			else{
				return "register";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "register";
		}
	}

	@RequestMapping(value = "/registerCheck", method = RequestMethod.GET, params = { "email" })
	public String userExists(@RequestParam String email, HttpServletRequest request) {
		String userExists = "false";
		UserLogon user = loginService.findUserByEmail(email);
		if (user != null) {
			userExists = "true";
		}
		return userExists;

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
