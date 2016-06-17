package org.givenkind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView launchLoginPage() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("home");
		return mav;		
	}
	
}
