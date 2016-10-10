package org.givenkind.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.givenkind.dto.LoginDTO;
import org.givenkind.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("loginDTO")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView launchLoginPage(@RequestParam(value="error", required=false) String error) {
		System.out.println("launchLoginPage::BEGIN");
		ModelAndView mav = new ModelAndView();
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setValid(false);
		mav.addObject(loginDTO);
		mav.setViewName("login");
		
		if(error != null) {
			mav.addObject("error", "Invalid username and password!");
		
		}
		logger.info("error msg "+error);
		logger.info("launchLoginPage::END");
		return mav;
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String accessDenied() {
		return "403";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		logger.debug("logout::BEGIN");
		ModelAndView mav = new ModelAndView();
		SecurityContextHolder.clearContext();
		mav.setViewName("redirect:/home");
		logger.debug("logout::END");
		return mav;
	}
	
}
