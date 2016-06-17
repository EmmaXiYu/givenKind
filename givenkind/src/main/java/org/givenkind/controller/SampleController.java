package org.givenkind.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

	@RequestMapping(value="/sample", method=RequestMethod.GET)
	public ModelAndView sample(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("welcomeMessage", "Welcome to giveNkind!");
		modelAndView.setViewName("sample");
		return modelAndView;
	}
	
}
