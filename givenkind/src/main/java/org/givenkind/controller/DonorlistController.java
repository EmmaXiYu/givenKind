/**
 * 
 */
package org.givenkind.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.givenkind.dto.DonorlistDTO;
import org.givenkind.service.DonorlistService;
import org.givenkind.service.ReferenceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller
public class DonorlistController extends AbstractProfileController {
	
	@Inject
	DonorlistService donorlistService;
	
	@Inject
	ReferenceDataService referenceDataService;
	
	private static final Logger log = LoggerFactory.getLogger(DonorlistController.class);

	//Handling when user first navigates to a donor list page
	@RequestMapping(value = "/donorlist", method = RequestMethod.GET)
	public String launchDonorListPage(Model model,
			@RequestParam(value="userId", required=false) Long userId, HttpSession session) {


		if (userId == null) {
			userId = getMyUserId();
		}
		
		DonorlistDTO dto = new DonorlistDTO();
		/*dto.setCondition("");
		dto.setDateExpires(new Date());
		dto.setDescription("");
		dto.setFairMarketValue(0.0);
		dto.setId(null);
		dto.setItemCategories(populateItemCategoryList());
		dto.setItemName("");
		dto.setQuantity(1);*/
		dto.setUserId(userId);
		model.addAttribute("donorlistDTO", dto);
		List<DonorlistDTO> items = donorlistService.getListOfDonatedItems(userId);
		model.addAttribute("donatedItems", items);
		return "donorlist";
	}
	
	
	//Handling when user first navigates to a donor list page
	@RequestMapping(value = "/adminDonorlist", method = RequestMethod.GET)
	public String adminDonorList(Model model,
			@RequestParam(value="userId", required=false) Long userId, HttpSession session) {

		DonorlistDTO dto = new DonorlistDTO();
		dto.setCondition("");
		dto.setDateExpires(new Date());
		dto.setDescription("");
		dto.setFairMarketValue(0.0);
		dto.setId(null);
		dto.setItemCategories(populateItemCategoryList());
		dto.setItemName("");
		dto.setQuantity(1);
		dto.setUserId(new Long(34));
		model.addAttribute("donorlistDTO", dto);
		List<DonorlistDTO> items= donorlistService.getListOfAllDonatedItems();
		model.addAttribute("donatedItems", items);
		return "adminviewDonatedItem";
	}
	
	//Handling when user wants to add an item
	@RequestMapping(value="/addToDonorlist", method = RequestMethod.POST)
	public String addToDonorlist(@Valid @ModelAttribute("donorlistDTO") DonorlistDTO donorlistDTO, BindingResult result,
			Model model, @RequestParam(value="userId", required=false) Long userId) {
		
		if (userId == null) {
			userId = getMyUserId();
			donorlistDTO.setUserId(userId);
		}
		
		if (result.hasErrors()){
			log.error("errors were "+Arrays.toString(result.getAllErrors().toArray()));
			return "donorlist";
		}
		
		donorlistService.addDonatedItem(donorlistDTO);
		
		log.info("added items");
		List<DonorlistDTO> items = donorlistService.getListOfDonatedItems(userId);
		
		model.addAttribute("donatedItems", items);
		
		DonorlistDTO dto = new DonorlistDTO();
		/*dto.setCondition("");
		dto.setDateExpires(new Date());
		dto.setDescription("");
		dto.setFairMarketValue(0.0);
		dto.setId(null);
		dto.setItemCategories(populateItemCategoryList());
		dto.setItemName("");
		dto.setQuantity(1);*/
		dto.setUserId(userId);
		model.addAttribute("donorlistDTO", dto);
		return "donorlist";
	}
	
	
	//Handling when user wants to add an item
	@RequestMapping(value="/adminAddToDonorlist", method = RequestMethod.POST)
	public String adminAddToDonorlist(@Valid @ModelAttribute("donorlistDTO") DonorlistDTO donorlistDTO, BindingResult result,
			Model model, @RequestParam(value="userId", required=false) Long userId) {
		
		if (userId == null) {
			userId = getMyUserId();
		}
		
		if (result.hasErrors()){
			log.error("errors were "+Arrays.toString(result.getAllErrors().toArray()));
			return "donorlist";
		}
		System.out.println(donorlistDTO.getUserId());
		donorlistService.adminAddDonatedItem(donorlistDTO);
		
		log.info("added items");
		
		
		DonorlistDTO dto = new DonorlistDTO();
		dto.setCondition("");
		dto.setDateExpires(new Date());
		dto.setDescription("");
		dto.setFairMarketValue(0.0);
		dto.setId(null);
		dto.setItemCategories(populateItemCategoryList());
		dto.setItemName("");
		dto.setQuantity(1);
		dto.setUserId(new Long(34));
		model.addAttribute("donorlistDTO", dto);
		List<DonorlistDTO> items= donorlistService.getListOfAllDonatedItems();
		model.addAttribute("donatedItems", items);
		return "adminviewDonatedItem";
	}

	@ModelAttribute("ItemCategoryList")
	public List<String> populateItemCategoryList() {
		return referenceDataService.getItemCategoryList();
	}

	//Handling request to delete item
	@RequestMapping(value = "/deleteDonation")
	public String deleteDonorItem(@RequestParam("donorId") Long id,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request) {
		if (userId == null) {
			userId = getMyUserId();
		}
		donorlistService.deleteDonatedItem(id);
		
		return "redirect:donorlist?userId=" + userId;
	}
	@RequestMapping(value = "/admindDeleteDonation")
	public String adminDeleteDonorItem(@RequestParam("donorId") Long id,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request) {
		if (userId == null) {
			userId = getMyUserId();
		}
		donorlistService.deleteDonatedItem(id);
		
		return "redirect:adminDonorlist?userId=" + 34;
	}
	
	
	

	//Handling when user clicks link to edit a donor list item
	@RequestMapping(value = "/editDonoritem", method = RequestMethod.GET)
	@Transactional
	public String editDonorItem(Model model, @RequestParam("donorId") Long id,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request){
		if (userId == null) {
			userId = getMyUserId();
		}
		DonorlistDTO donorlistDTO = donorlistService.getItemById(id);
		
		//Data comes out as timestamp; converting back to date type
		Date tempValue = donorlistDTO.getDateExpires();
		
		donorlistDTO.setDateExpires(tempValue);
		model.addAttribute("donorlistDTO", donorlistDTO);
		return "editDonoritem";
	}
	//Handling when user clicks link to edit a donor list item
		@RequestMapping(value = "/adminEditDonoritem", method = RequestMethod.GET)
		@Transactional
		public String adminEditDonorItem(Model model, @RequestParam("donorId") Long id,
				@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request){
			if (userId == null) {
				userId = getMyUserId();
			}
			DonorlistDTO donorlistDTO = donorlistService.getItemById(id);
			
			//Data comes out as timestamp; converting back to date type
			Date tempValue = donorlistDTO.getDateExpires();
			
			donorlistDTO.setDateExpires(tempValue);
			model.addAttribute("donorlistDTO", donorlistDTO);
			return "adminEditDonorItem";
		}
	
	//Completing edit request
	@RequestMapping(value = "/completeEditDonor", method = RequestMethod.POST)
	@Transactional
	public String completeEditDonor(@Valid @ModelAttribute("donorlistDTO") DonorlistDTO donorlistDTO, BindingResult result, Model model, 
			@RequestParam("donorId") Long id,
			@RequestParam(value="userId", required=false) Long userId, 
			//@RequestParam("dateExpires") Date date,
			HttpServletRequest request){
		
		if (userId == null) {
			userId = getMyUserId();
			donorlistDTO.setUserId(userId);
		}
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors().get(0));
			return "donorlist";
		}
		
		donorlistDTO.setId(id);		
		
		donorlistService.editDonatedItem(id, donorlistDTO);
		
		return "redirect:donorlist";
	}
	
	//Completing edit request
		@RequestMapping(value = "/adminCompleteEditDonor", method = RequestMethod.POST)
		@Transactional
		public String adminCompleteEditDonor(@Valid @ModelAttribute("donorlistDTO") DonorlistDTO donorlistDTO, BindingResult result, Model model, 
				@RequestParam("donorId") Long id,
				@RequestParam(value="userId", required=false) Long userId, 
				//@RequestParam("dateExpires") Date date,
				HttpServletRequest request){
			
			if (userId == null) {
				userId = getMyUserId();
				donorlistDTO.setUserId(userId);
			}
			
			if (result.hasErrors()) {
				System.out.println(result.getAllErrors().get(0));
				return "donorlist";
			}
			
			donorlistDTO.setId(id);		
			
			donorlistService.editDonatedItem(id, donorlistDTO);
			
			DonorlistDTO dto = new DonorlistDTO();
			dto.setCondition("");
			dto.setDateExpires(new Date());
			dto.setDescription("");
			dto.setFairMarketValue(0.0);
			dto.setId(null);
			dto.setItemCategories(populateItemCategoryList());
			dto.setItemName("");
			dto.setQuantity(1);
			dto.setUserId(new Long(34));
			model.addAttribute("donorlistDTO", dto);
			List<DonorlistDTO> items= donorlistService.getListOfAllDonatedItems();
			model.addAttribute("donatedItems", items);
			return "adminviewDonatedItem";
		}
		
}
