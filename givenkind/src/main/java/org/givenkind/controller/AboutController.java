package org.givenkind.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView launchAboutPage() {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("aboutus");
		return mav;		
	}
	 @RequestMapping("/pdf/{fileName:.+}")
	    public void downloadPDFResource( HttpServletRequest request, 
	                                     HttpServletResponse response, 
	                                     @PathVariable("fileName") String fileName) 
	    {
		 	   
	        String dataDirectory = request.getServletContext().getRealPath("/pdf/");
	        System.out.println(dataDirectory);
	        Path file = Paths.get(dataDirectory, fileName);
	        if (Files.exists(file)) 
	        {	        	
	            response.setContentType("application/pdf");
	            response.addHeader("Content-Disposition", "inline; filename="+fileName);
	            try
	            {	            	
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            } 
	            catch (IOException ex) {	            	
	                ex.printStackTrace();
	            }
	        }
	    }
	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public ModelAndView launchFAQPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("faq");
		return mav;		
	}
	
	@RequestMapping(value ="/tac", method = RequestMethod.GET)
	public ModelAndView launchTACPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tac");
		return mav;		
	}
	
	@RequestMapping(value ="/contact", method = RequestMethod.GET)
	public ModelAndView launchContactPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contact");
		return mav;		
	}
	
	@RequestMapping(value ="/NPFhiw", method = RequestMethod.GET)
	public ModelAndView launchNPFhiwPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("NPFhiw");
		return mav;		
	}
	
	@RequestMapping(value ="/Dhiw", method = RequestMethod.GET)
	public ModelAndView launchDhiwPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Dhiw");
		return mav;		
	}
	
}