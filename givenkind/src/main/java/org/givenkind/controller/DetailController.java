package org.givenkind.controller;

import java.util.ArrayList;
import java.util.List;

import org.givenkind.dto.ActiveTransactionItemsDTO;
import org.givenkind.dto.DonorlistDTO;
import org.givenkind.dto.WishlistDTO;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.WishlistItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/itemDetails")
public class DetailController extends AbstractProfileController{

	@Autowired
	org.givenkind.repository.DonorlistItemRepository donorItemRepo;
	
	@Autowired
	org.givenkind.repository.WishlistItemRepository wishlistItemRepo;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private List<String> itemCategoryNames(List<ItemCategory> categories) {
		List<String> lst = new ArrayList<String>();
		
		for(ItemCategory c : categories) {
			lst.add(c.getCategoryName());
		}
		
		return lst;
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="donor")
	public ModelAndView displayDonorItemsDetailsPage(@RequestParam(value="itemId", required=true) Long itemId) {
		ModelAndView mav = new ModelAndView();
		
		//Add check for transaction in progress here?
		
		mav.setViewName("detail");
		
		
		DonorlistItem item = donorItemRepo.findById(itemId);
		if(item == null) {
			log.info("no donor item found with id "+itemId);
			mav.addObject("error", "No such item found");
		} else {
			DonorlistDTO dto = new DonorlistDTO();
			Long test = getMyUserId();
			dto.setCondition(item.getCondition());
			dto.setDateExpires(item.getDateExpires());
			dto.setDescription(item.getDescription());
			dto.setFairMarketValue(item.getFairMarketValue());
			dto.setId(item.getId());
			dto.setItemCategories(this.itemCategoryNames(item.getItemCategories()));
			dto.setItemName(item.getItemName());
			dto.setQuantity(item.getQuantity());
			dto.setUserId(item.getUser().getId());
			mav.addObject("donorlistDTO", dto);
			log.info("item is "+dto);
		}
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="nonprofit")
	public ModelAndView displayNonProfitItemDetailsPage(@RequestParam(value="itemId", required=true) Long itemId) {
		ModelAndView mav = new ModelAndView();
		
		//Add check for transaction in progress here?
		
		mav.setViewName("detail");
		
		
		WishlistItem item = this.wishlistItemRepo.findById(itemId);
		if(item == null) {
			log.info("no wishlist item found with id "+itemId);
			mav.addObject("error", "No such item found");
		} else {
			WishlistDTO dto = new WishlistDTO();
			ActiveTransactionItemsDTO transDTO = new ActiveTransactionItemsDTO();
			Long test = this.getMyUserId();
			dto.setdateExpires(item.getDateExpires());
			dto.setId(item.getId());
			dto.setImpact(item.getImpact());
			dto.setItemName(item.getItemName());
			dto.setNote(item.getNotes());
			dto.setQuantityDesired(item.getQuantityDesired());
			dto.setUserId(item.getNonProfitUserLogon().getId());
			dto.setWishlistItemCategories(this.itemCategoryNames(item.getWishlistItemCategories()));
			mav.addObject("wishlistDTO", dto);
			log.info("dto is "+dto);

			mav.addObject("activeTransactionItemsDTO", transDTO);
			
		}
		
		return mav;
	}
}
