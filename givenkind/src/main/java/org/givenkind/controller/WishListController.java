package org.givenkind.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.givenkind.dto.WishlistDTO;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.WishlistItem;
import org.givenkind.service.ProfileService;
import org.givenkind.service.ReferenceDataService;
import org.givenkind.service.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishListController extends AbstractProfileController {

	private static Logger log = LoggerFactory.getLogger(WishListController.class);
	
	@Inject
	WishlistService wishlistService;

	@Inject
	ProfileService profileService;
	
	@Inject
	ReferenceDataService referenceDataService;
	
	//Handling request when user navigates to wishlist page
	@RequestMapping(value = "/wishlist", method = RequestMethod.GET)
	public String launchWishListPage(Model model,
			@RequestParam(value="userId", required=false) Long userId, HttpSession session) {

		if (userId == null) {
			userId = getMyUserId();
		}
		
		List<WishlistDTO> wishlist = wishlistService.getWishesForUser(userId);
		model.addAttribute("wishlistItems", wishlist);
		model.addAttribute("userId", userId);
		model.addAttribute("wishlistDTO", new WishlistDTO());
		return "wishlist";
	}

	//Handling request when user wants to add a wishlist item
	@RequestMapping(value="/addWish",method = RequestMethod.POST)
	public String addWish(@Valid @ModelAttribute("wishlistDTO") WishlistDTO wishlistDTO, BindingResult result, Model model,
			@RequestParam(value="userId", required=false) Long userId) {

		if (userId == null) {
			userId = getMyUserId();
			wishlistDTO.setUserId(userId);
		}
		
		if (result.hasErrors()) {
			return "wishlist";
		}
		
		wishlistService.addWish(wishlistDTO);
		List<WishlistDTO> items = wishlistService.getWishesForUser(userId);
		model.addAttribute("wishlistDTO", new WishlistDTO());
		model.addAttribute("wishlistItems", items);
		return "wishlist";
	}

	

	@RequestMapping(value="/adminAddWish",method = RequestMethod.POST)
	public String adminAddWish(@Valid @ModelAttribute("wishlistDTO") WishlistDTO wishlistDTO, BindingResult result, Model model,
			@RequestParam(value="userId", required=false) Long userId) {

		if (userId == null) {
			userId = getMyUserId();
			wishlistDTO.setUserId(new Long(18));
		}
		
		if (result.hasErrors()) {
			return "adminviewWishes";
		}
		
		wishlistService.adminAddWish(wishlistDTO);
		List<WishlistDTO> items = wishlistService.getAllWishes();

		model.addAttribute("wishlistItems", items);
		return "adminviewWishes";
	}

	@ModelAttribute("ItemCategoryList")
	public List<String> populateItemCategoryList() {
		return referenceDataService.getItemCategoryList();
	}


	//Handline request to delete wish
	@RequestMapping(value = "/deleteWish", method = RequestMethod.GET)
	public String deleteWish(@RequestParam("wishId") Long id,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request) {
		if (userId == null) {
			userId = getMyUserId();
		}
		
		wishlistService.deleteWish(id);
		
		return "redirect:wishlist?userId=" + userId;
	}
	
	//Handling request when user navigates to wishlist page
			@RequestMapping(value = "/adminWishlist", method = RequestMethod.GET)
			public String adminWishList(Model model,
					@RequestParam(value="userId", required=false) Long userId, HttpSession session) {

				if (userId == null) {
					userId = getMyUserId();
				}
				
				WishlistDTO dto=new WishlistDTO();
				dto.setdateExpires(new Date());
				dto.setId(null);
				dto.setImpact("");
				dto.setItemName("");
				dto.setNote("");
				dto.setQuantityDesired(0);
				dto.setUserId(new Long(18));
				dto.setWishlistItemCategories(populateItemCategoryList());
				List<WishlistDTO> items = wishlistService.getAllWishes();
				model.addAttribute("wishlistDTO", dto);
				model.addAttribute("wishlistItems", items);
				return "adminviewWishes";
	
			}

	

	//Handline request to delete wish
	@RequestMapping(value = "/adminDeleteWish", method = RequestMethod.GET)
	public String adminDeleteWish(@RequestParam("wishId") Long id,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request) {
		if (userId == null) {
			userId = getMyUserId();
		}
		
		wishlistService.deleteWish(id);
		
		return "redirect:adminWishlist?userId=" + 18;
	}
	
	@Inject org.givenkind.repository.WishlistItemRepository wishlistItemRepo;
	
	//Handling when user clicks link to edit a wishlist item 
	@RequestMapping(value = "/editWish", method = RequestMethod.GET)
	@Transactional
	public String editWish(Model model, @RequestParam("wishId") Long id,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request){
		if (userId == null) {
			userId = getMyUserId();
		}
		
		
		WishlistItem item = wishlistItemRepo.findOne(id);
		WishlistDTO dto = convertWishlistItemToWishlistDTO(item);
		
		model.addAttribute("wishlistDTO", dto);
		return "editWish";
	}
	
	
	
	//Handling when user clicks link to edit a wishlist item 
	@RequestMapping(value = "/adminEditWish", method = RequestMethod.GET)
	@Transactional
	public String adminEditWish(Model model, @RequestParam("wishId") Long id,
			@RequestParam(value="userId", required=false) Long userId, HttpServletRequest request){
		if (userId == null) {
			userId = getMyUserId();
		}
		
		
		WishlistItem item = wishlistItemRepo.findOne(id);
		WishlistDTO dto = convertWishlistItemToWishlistDTO(item);
		System.out.println("userid:"+ dto.getUserId());
		model.addAttribute("wishlistDTO", dto);
		return "admineditwish";
	}
	
	private WishlistDTO convertWishlistItemToWishlistDTO(WishlistItem item) {
		WishlistDTO dto = new WishlistDTO();
		Date dateExpires = item.getDateExpires();
		Long id = item.getId();
		dto.setdateExpires(dateExpires);
		dto.setId(id);
		String impact = item.getImpact();
		dto.setImpact(impact);
		String itemName = item.getItemName();
		dto.setItemName(itemName);
		String note = item.getNotes();
		dto.setNote(note);
		Integer quantityDesired = item.getQuantityDesired();
		dto.setQuantityDesired(quantityDesired);
		
		Long userLogonId = item.getNonProfitUserLogon().getId();
		dto.setUserId(userLogonId);
		
		dto.setWishlistItemCategories(itemCategoriesToStringList(item.getWishlistItemCategories()));
		
		return dto;
	}
	
	private List<String> itemCategoriesToStringList(List<ItemCategory> lst) {
		List<String> l = new ArrayList<String>();
		for(ItemCategory c : lst) {
			l.add(c.getCategoryName());
		}
		return l;
	}

	//Completing edit request
	@RequestMapping(value = "/editWish" , method = RequestMethod.POST)
	@Transactional
	public String completeEditWish(@ModelAttribute("wishlistDTO") WishlistDTO wishlistDTO, BindingResult result, Model model, 
			@RequestParam("wishId") Long id,
			@RequestParam(value="userId", required=false) Long userId, 
			HttpServletRequest request){
		
		log.info("reached completeEditWish");
		
		if (userId == null) {
			userId = getMyUserId();
			wishlistDTO.setUserId(userId);
		}
		
		if (result.hasErrors()) {
			
			for(ObjectError e : result.getAllErrors()) {
				log.error("error when editing wish: "+e);
			}
			
			return "wishlist";
		}
		
		wishlistDTO.setId(id);		
		
		log.info("saving new wishlist item");
		
		wishlistService.editWish(id, wishlistDTO);
		
		return "redirect:wishlist";
	}
	
	
	//Completing edit request
		@RequestMapping(value = "/adminEditWish" , method = RequestMethod.POST)
		@Transactional
		public String completeAdminEditWish(@ModelAttribute("wishlistDTO") WishlistDTO wishlistDTO, BindingResult result, Model model, 
				@RequestParam("wishId") Long id,
				@RequestParam(value="userId", required=false) Long userId, 
				HttpServletRequest request){
			
			log.info("reached completeEditWish");
			
			if (userId == null) {
				userId = getMyUserId();
				wishlistDTO.setUserId(new Long(18));
			}
			
			if (result.hasErrors()) {
				
				for(ObjectError e : result.getAllErrors()) {
					log.error("error when editing wish: "+e);
				}
				
				return "wishlist";
			}
			
			wishlistDTO.setId(id);		
			
			log.info("saving new wishlist item");
			
			wishlistService.editWish(id, wishlistDTO);
			
			
			WishlistDTO dto=new WishlistDTO();
			dto.setdateExpires(new Date());
			dto.setId(null);
			dto.setImpact("");
			dto.setItemName("");
			dto.setNote("");
			dto.setQuantityDesired(0);
			dto.setUserId(new Long(18));
			dto.setWishlistItemCategories(populateItemCategoryList());
			List<WishlistDTO> items = wishlistService.getAllWishes();
			model.addAttribute("wishlistDTO", dto);
			model.addAttribute("wishlistItems", items);
			return "adminviewWishes";
			
		}
	
}
