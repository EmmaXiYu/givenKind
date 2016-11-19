package org.givenkind.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.givenkind.dto.ActiveTransactionItemsDTO;
import org.givenkind.dto.CompletedTransactionsDTO;
import org.givenkind.dto.DonorlistDTO;
import org.givenkind.dto.ProfileDTO;
import org.givenkind.dto.WishlistDTO;
import org.givenkind.model.ActiveTransactionItems;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.WishlistItem;
import org.givenkind.repository.ActiveTransactionItemsRepository;
import org.givenkind.repository.DonorlistItemRepository;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.repository.StatusCategoryRepository;
import org.givenkind.repository.WishlistItemRepository;
import org.givenkind.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DonorTransactionController extends AbstractProfileController{
	
	@Autowired
	org.givenkind.repository.WishlistItemRepository wishlistItemRepo;
	
	@Inject
	StatusCategoryRepository statusCategoryRepository;
	
	@Inject
	ProfileRepository profileRepository;
	
	@Inject
	TransactionService transactionService;
	
	@Inject
	ActiveTransactionItemsRepository activeTransactionItemsRepository;
	
	@Inject
	DonorlistItemRepository donorlistItemRepository;
	
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@ModelAttribute("transDTO")
	@RequestMapping(value = "/itemDetails/startDonorTransaction", method = RequestMethod.GET)
	public ModelAndView startTransaction(@RequestParam(value="itemId", required=false) Long itemId){
		ModelAndView mav = new ModelAndView();
		
		WishlistItem item = wishlistItemRepo.findById(itemId);
		if(item == null) {
			log.info("no donor item found with id "+itemId);
			mav.addObject("error", "No such item found");
		} else {
			WishlistDTO wishDTO = new WishlistDTO();
			wishDTO.setItemName(item.getItemName());
			wishDTO.setId(item.getId());
			wishDTO.setQuantityDesired(item.getQuantityDesired());
			mav.addObject("wishDTO", wishDTO);
			
			ProfileDTO donorDTO = new ProfileDTO();
			donorDTO.setContactEmail(this.getMyUsername());
			mav.addObject("donorDTO", donorDTO);
			ProfileDTO npDTO = new ProfileDTO();
			npDTO.setContactEmail(item.getNonProfitUserLogon().getLoginId());
			mav.addObject("npDTO", npDTO);
		}
		mav.addObject("transDTO", new ActiveTransactionItemsDTO());
		mav.setViewName("/startDonorTransaction");
		return mav;
	}
	
	@RequestMapping(value = "/itemDetails/finishAddingDonorTransaction", method = RequestMethod.POST)
	public String finishAddingDonorTransaction(@Valid @ModelAttribute("transDTO") ActiveTransactionItemsDTO transDTO
				, Model model, @RequestParam(value="itemId", required=false) Long itemId){
				
		WishlistItem item = wishlistItemRepo.findById(itemId);
		if(item == null) {
			log.info("no donor item found with id "+itemId);
		} else {
			transDTO.setNpItem(item);
			transDTO.setNpProfile(item.getNonProfitUserLogon().getProfile());
			log.info("item is "+ transDTO);
		}		
		
		transDTO.setDonorProfile(profileRepository.findById(getMyUserId()));
		transDTO.setStatusCategory(statusCategoryRepository.findByStatusCategoryName("Donor Requested"));
		transactionService.startTransaction(transDTO,false);
		
		
		return "redirect:/donorTransactions";
	}
	
	//Handling initial page launch
	@RequestMapping(value = "/donorTransactions", method = RequestMethod.GET)
	public ModelAndView launchDonorTransactionPage(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		Long userId = getMyUserId();
		if (userId == null) {
			log.info("no user found with id "+userId);
			mav.addObject("error", "No user found");
		}
		
		List<ActiveTransactionItemsDTO> transList = 
				transactionService.getActiveTransactions(userId,false);
		mav.addObject("transList", transList);
		
		mav.setViewName("/donorTransactions");
		return mav;
	}
	
	//Canceling or rejecting an active transaction
	@RequestMapping(value = "/cancelDonorTransaction", method = RequestMethod.GET)
	public String cancelDonorTransaction(@RequestParam("transactionId") Long id,
			HttpServletRequest request){
		
		transactionService.deleteTransaction(id);
		return "redirect:donorTransactions";
	}
	@RequestMapping(value = "/cancelAcceptedDonorTransaction", method = RequestMethod.GET)
	public String cancelAcceptedDonorTransaction(@RequestParam("transactionId") Long id,
			HttpServletRequest request){
		
		transactionService.deleteAcceptedTransaction(id);
		return "redirect:donorTransactions";
	}
	
	@RequestMapping(value = "/confirmDonorTransaction", method = RequestMethod.GET)
	public String confirmDonorTransaction(@RequestParam("transactionId") Long id,@RequestParam("qty") int qty
			, HttpServletRequest request,RedirectAttributes redirectAttrs){
		
		ActiveTransactionItems itemToChange = activeTransactionItemsRepository.findById(id);
		DonorlistItem dItem = donorlistItemRepository.findById(itemToChange.getDonorItemId());		
		if(qty!=0){
			if(qty<=itemToChange.getQuantity()){
				if(dItem.getQuantity()>= qty){
					transactionService.updateStatus(id, "Accepted",qty);
					log.info("Status accepted ");
								                   
				}
				else if(dItem.getQuantity()==0){
					redirectAttrs.addFlashAttribute("msg","You have already donated all your items.");
					log.info("Status Not accepted ");
					
				}
				else{
					redirectAttrs.addFlashAttribute("msg","Not Allowed to accept more than you have. Please edit to change the quantity");
					log.info("Status Not accepted ");
					
				}}
			else{
				redirectAttrs.addFlashAttribute("msg","Not Allowed to enter more than NonProfit requested. Please edit to change the quantity");
				log.info("Status Not accepted ");
				
			}
		}
		else{
			redirectAttrs.addFlashAttribute("msg","Please enter valid Quantity");
			log.info("Status Not accepted ");
			
		}
		
		return "redirect:donorTransactions";
	}
	
	@RequestMapping(value = "/confirmDonorShipment", method = RequestMethod.GET)
	public String confirmDonorShipment(@RequestParam("transactionId") Long id,
			HttpServletRequest request){
		transactionService.updateTransitStatus(id, "In Transit");
		
		return "redirect:donorTransactions";
	}
	@RequestMapping(value = "/completedDonortxn", method = RequestMethod.GET)
	public ModelAndView launchCompletedTxnPage(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		Long userId = getMyUserId();
		if (userId == null || userId == -1) {
			log.info("no user found with id "+userId);
			mav.addObject("error", "No user found");
		}
		
		List<CompletedTransactionsDTO> transList = 
				transactionService.getCompletedTransactions(userId,false);
		model.addAttribute("transList", transList);
		
		mav.setViewName("/donorCompletedTransactions");
		return mav;
	}
}