package org.givenkind.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.givenkind.dto.ActiveTransactionItemsDTO;
import org.givenkind.dto.CompletedTransactionsDTO;
import org.givenkind.dto.DonorlistDTO;
import org.givenkind.dto.ProfileDTO;
import org.givenkind.model.ActiveTransactionItems;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.WishlistItem;
import org.givenkind.repository.ActiveTransactionItemsRepository;
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
public class npTransactionController extends AbstractProfileController{
	
	@Autowired
	org.givenkind.repository.DonorlistItemRepository donorlistItemRepo;
	
	@Inject
	StatusCategoryRepository statusCategoryRepository;
	
	@Inject
	ProfileRepository profileRepository;
	
	@Inject
	TransactionService transactionService;
	
	@Inject
	ActiveTransactionItemsRepository activeTransactionItemsRepository;
	
	@Inject
	WishlistItemRepository wishlistItemRepository;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@ModelAttribute("transDTO")
	@RequestMapping(value = "/itemDetails/startNpTransaction", method = RequestMethod.GET)
	public ModelAndView startTransaction(@RequestParam(value="itemId", required=false) Long itemId){
		ModelAndView mav = new ModelAndView();
		
		DonorlistItem item = donorlistItemRepo.findById(itemId);
		if(item == null) {
			log.info("no donor item found with id "+itemId);
			mav.addObject("error", "No such item found");
		} else {
			DonorlistDTO donorItemDTO = new DonorlistDTO();
			donorItemDTO.setItemName(item.getItemName());
			donorItemDTO.setId(item.getId());
			donorItemDTO.setQuantity(item.getQuantity());
			mav.addObject("donorItemDTO", donorItemDTO);
			
			ProfileDTO npDTO = new ProfileDTO();
			npDTO.setContactEmail(this.getMyUsername());
			mav.addObject("npDTO", npDTO);
			ProfileDTO donorDTO = new ProfileDTO();
			donorDTO.setContactEmail(item.getUser().getLoginId());
			mav.addObject("donorDTO", donorDTO);
		}
		mav.addObject("transDTO", new ActiveTransactionItemsDTO());
		mav.setViewName("/startNpTransaction");
		
		return mav;
	}
	
	@RequestMapping(value = "/itemDetails/finishAddingNpTransaction", method = RequestMethod.POST)
	public String finishAddingDonorTransaction(@Valid @ModelAttribute("transDTO") ActiveTransactionItemsDTO transDTO
				, Model model, @RequestParam(value="itemId", required=false) Long itemId){
		
				
		DonorlistItem item = donorlistItemRepo.findById(itemId);
		if(item == null) {
			log.info("no donor item found with id "+itemId);
		} else {
			transDTO.setDonorItem(item);
			transDTO.setDonorProfile(item.getUser().getProfile());
			log.info("item is "+ transDTO);
		}		
		
		transDTO.setNpProfile(profileRepository.findById(getMyUserId()));
		transDTO.setStatusCategory(statusCategoryRepository.findByStatusCategoryName("NP Requested"));
		transactionService.startTransaction(transDTO,true);
		
		
		return "redirect:/npTransactions";
	}
	
	@RequestMapping(value = "/npTransactions", method = RequestMethod.GET)
	public ModelAndView launchTransactionPage(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		Long userId = getMyUserId();
		if (userId == null || userId == -1) {
			log.info("no user found with id "+userId);
			mav.addObject("error", "No user found");
		}
		
		List<ActiveTransactionItemsDTO> transList = 
				transactionService.getActiveTransactions(userId,true);
		model.addAttribute("transList", transList);
		
		mav.setViewName("/npTransactions");
		return mav;
	}
	@RequestMapping(value = "/completedNPtxn", method = RequestMethod.GET)
	public ModelAndView launchCompletedTxnPage(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		Long userId = getMyUserId();
		if (userId == null || userId == -1) {
			log.info("no user found with id "+userId);
			mav.addObject("error", "No user found");
		}
		
		List<CompletedTransactionsDTO> transList = 
				transactionService.getCompletedTransactions(userId,true);
		model.addAttribute("transList", transList);
		
		mav.setViewName("/npCompletedTransactions");
		return mav;
	}
	
	@RequestMapping(value = "/cancelNpTransaction", method = RequestMethod.GET)
	public String cancelNpTransaction(@RequestParam("transactionId") Long id,
			 HttpServletRequest request){

		transactionService.deleteTransaction(id);	
		return "redirect:npTransactions";
	}
	
	@RequestMapping(value = "/cancelNpAcceptedTransaction", method = RequestMethod.GET)
	public String cancelNPAcceptedTransaction(@RequestParam("transactionId") Long id,
			 HttpServletRequest request){

		transactionService.deleteAcceptedTransaction(id);	
		return "redirect:npTransactions";
	}
	
	@RequestMapping(value = "/confirmNpTransaction", method = RequestMethod.GET)
	public String confirmNpTransaction(@RequestParam("transactionId") Long id,@RequestParam("qty") int qty,
			HttpServletRequest request,RedirectAttributes redirectAttrs){
		
		log.info("inside confirmation ");
		System.out.println("viji"+qty);
		
		ActiveTransactionItems itemToChange = activeTransactionItemsRepository.findById(id);
		WishlistItem wItem = wishlistItemRepository.findById(itemToChange.getWishItemId());		
		if(qty!=0){
			if(wItem.getQuantityDesired()>= qty){
				transactionService.updateStatus(id, "Accepted",qty);
				log.info("Status accepted ");
							
			}
			else if(wItem.getQuantityDesired()==0){
				redirectAttrs.addFlashAttribute("msg","Your wishlist is already fulfilled. Please cancel the request.");
				log.info("Status Not accepted ");
				
			}
			else{
				redirectAttrs.addFlashAttribute("msg","Not Allowed to accept more than required. Please edit to change the quantity");
				log.info("Status Not accepted ");
				
			}
		}
		else{
			redirectAttrs.addFlashAttribute("msg","Please enter valid Quantity");
			log.info("Status Not accepted ");
			
		}
		
		
		return "redirect:npTransactions";
	}
	
	@RequestMapping(value = "/confirmNpRecipt", method = RequestMethod.GET)
	public String confirmRecept(@RequestParam("transactionId") Long id, HttpServletRequest request){
		
		transactionService.completeTransaction(id);	
		return "redirect:npTransactions";
	}
}