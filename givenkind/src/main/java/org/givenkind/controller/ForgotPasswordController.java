package org.givenkind.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.givenkind.dto.ForgotPasswordDTO;
import org.givenkind.dto.PasswordResetAuthorizationDTO;
import org.givenkind.dto.PasswordResetDTO;
import org.givenkind.repository.PasswordResetRepository;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.LoginService;
import org.givenkind.service.NoSuchPasswordResetAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgotPasswordController {

	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
		
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView launchForgotPassword() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forgot");
		mav.addObject("forgotPasswordDTO", new ForgotPasswordDTO());
		return mav;
	}
	
	@Autowired
	PasswordResetRepository passwordResetRepository;
	
	@Autowired 
	UserLogonRepository userRepo; 
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	public String submitNewPassword(ModelAndView mav, @Valid @ModelAttribute("passwordResetDTO") PasswordResetDTO dto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "resetPassword";
		}
		
		try {			
			loginService.resetPassword(dto);
		} catch (NoSuchPasswordResetAuthorizationException ex) {
			mav.addObject("error", "invalid password reset request link");
		}
		
		mav.addObject("msg", "password reset successfully");
		
		return "login";
	}
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.GET)
	public ModelAndView  launchEnterNewPassword(@RequestParam(value = "id", required = false, defaultValue="0") long passwordResetAuthorizationId,
			@RequestParam(value = "uniqueResetKey", required = false, defaultValue="") String uniqueResetKey) {
		
		ModelAndView mav = new ModelAndView();
		PasswordResetDTO passwordResetDTO = new PasswordResetDTO();
		passwordResetDTO.setUniqueResetKey(uniqueResetKey);
		passwordResetDTO.setPasswordResetAuthorizationId(passwordResetAuthorizationId);
		passwordResetDTO.setNewPassword("");
		
		mav.addObject("passwordResetDTO", passwordResetDTO);
		mav.setViewName("passwordReset");
		return mav;
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ModelAndView processForgotPassword(ModelAndView mav, @Valid @ModelAttribute("forgotPasswordDTO") ForgotPasswordDTO dto, BindingResult bindingResult,HttpServletRequest req) {
		
		boolean status=false;
		mav.setViewName("forgot");
		
		if(bindingResult.hasErrors()) {
			return mav;
		}
		
		try {			
			String httpURL = "http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/";
			System.out.println("http url:"+httpURL );
			status=loginService.forgotPassword(dto,httpURL);
		} catch (Exception ex) {
			logger.info("exception thrown");
			// silently ignore exception - no need to tell user that no such user exists
		}
		
		logger.info("password reset requested for "+dto.getEmail());
		
		
		if(status){
			logger.info("Reset link sent");				
			mav.addObject("msg", "Reset password link is sent to your registered Email address");
			
		}else{	
			logger.info("Reset link  not sent");
			mav.addObject("msg", "Not a Registered Email address");
		
		}
		return mav;
	}
}
