package org.givenkind.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.dto.DonorlistDTO;
import org.givenkind.dto.WishlistDTO;
import org.givenkind.model.Profile;
import org.givenkind.service.DonorlistService;
import org.givenkind.service.ProfileService;
import org.givenkind.service.ReferenceDataService;
import org.givenkind.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Inject
	DonorlistService donorlistService;
	@Inject
	ReferenceDataService referenceDataService;
	@Inject 
	WishlistService wishlistService;
	
	@RequestMapping(value="/status", method = RequestMethod.GET)
	public String adminViewUserStatus(Model model) {
		List<Profile> profileList = profileService.prepareAdminPage();
		model.addAttribute("profileList", profileList);
		model.addAttribute("profileList");
		return "adminviewstatus";			
	}
	
	@RequestMapping(value="/donatedItem", method = RequestMethod.GET)
	public String adminViewDonatedItem(Model model) {
		DonorlistDTO dto = new DonorlistDTO();
		dto.setCondition("");
		dto.setDateExpires(new Date());
		dto.setDescription("");
		dto.setFairMarketValue(0.0);
		dto.setId(null);
		dto.setItemCategories(populateItemCategoryList());
		dto.setItemName("");
		dto.setQuantity(1);
		dto.setUserId(new Long(18));//need to be updated
		model.addAttribute("donorlistDTO", dto);
		List<DonorlistDTO> items= donorlistService.getListOfAllDonatedItems();
		model.addAttribute("donatedItems", items);
		
		return "adminviewDonatedItem";			
	}
	
	
	@RequestMapping(value="/wishList", method = RequestMethod.GET)
	public String adminViewWishList(Model model) {
		WishlistDTO dto = new WishlistDTO();
		dto.setdateExpires(new Date());
		dto.setId(null);
		dto.setImpact(null);
		dto.setItemName("");
		dto.setNote("");
		dto.setQuantityDesired(1);
	    dto.setWishlistItemCategories(populateItemCategoryList());
		dto.setUserId(new Long(18));
		model.addAttribute("wishlistDTO", dto);
		List<WishlistDTO> wishlistItems= wishlistService.getAllWishes();
		System.out.println("The size of wishlistItems is:" +wishlistItems.size());
		model.addAttribute("wishlistItems", wishlistItems);
		
		return "adminviewWishes";			
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET, params = {"userName"})
	public ModelAndView launchDetailPage(@RequestParam String userName){
		ModelAndView mav = new ModelAndView();
		String userId = profileService.retrieveUserIdFromUsername(userName);
		mav.addObject("profileDTO", getAdminProfileData(userId));
		mav.setViewName("/npfprofile");
		return mav;
	}
	@ModelAttribute("ItemCategoryList")
	public List<String> populateItemCategoryList() {
		return referenceDataService.getItemCategoryList();
	}

}