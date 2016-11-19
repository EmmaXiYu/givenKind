package org.givenkind.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.dto.ActiveTransactionItemsDTO;
import org.givenkind.dto.CompletedTransactionsDTO;
import org.givenkind.model.ActiveTransactionItems;
import org.givenkind.model.CompletedTransactions;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.Profile;
import org.givenkind.model.StatusCategory;
import org.givenkind.model.WishlistItem;
import org.givenkind.repository.ActiveTransactionItemsRepository;
import org.givenkind.repository.CompletedTransactionsRepository;
import org.givenkind.repository.DonorlistItemRepository;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.repository.StatusCategoryRepository;
import org.givenkind.repository.WishlistItemRepository;
import org.givenkind.service.TransactionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Inject
	DonorlistItemRepository donorlistItemRepository;
	
	@Inject
	WishlistItemRepository wishlistItemRepository;
	
	@Inject
	ProfileRepository profileRepository;
	
	@Inject
	ActiveTransactionItemsRepository activeTransactionItemsRepository;
	
	@Inject
	CompletedTransactionsRepository completedTransactionRepository;
	
	@Inject
	StatusCategoryRepository statusCategoryRepository;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	@Transactional
	public void startTransaction(ActiveTransactionItemsDTO toDB, boolean isNP){
		ActiveTransactionItems item = new ActiveTransactionItems();
		if(isNP){
		item.setDonorItem(toDB.getDonorItem().getId());
		}
		else{
			item.setWishItemId(toDB.getNpItem().getId());
		}
		item.setDonorProfile(toDB.getDonorProfile().getId());
		item.setNpProfile(toDB.getNpProfile().getId());
		item.setQuantity(toDB.getQuantity());
		item.setStatusCategoryId(toDB.getStatusCategory().getId());
		
		activeTransactionItemsRepository.save(item);
	}
	
	@Override
	public List<ActiveTransactionItemsDTO> getActiveTransactions(Long userId, boolean isNP) {
		List<ActiveTransactionItems> activeTransItems = new ArrayList<ActiveTransactionItems>();
		List<ActiveTransactionItemsDTO> activeTransItemsDTO = new ArrayList<ActiveTransactionItemsDTO>();
		Profile profile = profileRepository.findById(userId);
		if(isNP){
			activeTransItems = 
					activeTransactionItemsRepository.findByNpProfileId(userId);
			for(ActiveTransactionItems activeTransItem : activeTransItems){
				ActiveTransactionItemsDTO toList = new ActiveTransactionItemsDTO();
				WishlistItem yourItem = wishlistItemRepository.findById(activeTransItem.getWishItemId());
				DonorlistItem item = donorlistItemRepository.findById(activeTransItem.getDonorItemId());
				StatusCategory category = statusCategoryRepository.findById(activeTransItem.getStatusCategoryId());
				Profile donorProfile = profileRepository.findById(activeTransItem.getDonorProfileId());
				Profile npProfile = profileRepository.findById(activeTransItem.getNpProfileId());
				
				toList.setId(activeTransItem.getId());
				toList.setNpItem(yourItem);
				toList.setDonorItem(item);
				toList.setDonorProfile(donorProfile);
				toList.setNpProfile(npProfile);
				toList.setQuantity(activeTransItem.getQuantity());
				toList.setStatusCategory(category);
				activeTransItemsDTO.add(toList);
			}
		}
		else{
			activeTransItems = 
					activeTransactionItemsRepository.findByDonorProfileId(userId);
			for(ActiveTransactionItems activeTransItem : activeTransItems){
				ActiveTransactionItemsDTO toList = new ActiveTransactionItemsDTO();
				DonorlistItem yourItem = donorlistItemRepository.findById(activeTransItem.getDonorItemId());
				WishlistItem item = wishlistItemRepository.findById(activeTransItem.getWishItemId());
				StatusCategory category = statusCategoryRepository.findById(activeTransItem.getStatusCategoryId());				
				Profile donorProfile = profileRepository.findById(activeTransItem.getDonorProfileId());
				Profile npProfile = profileRepository.findById(activeTransItem.getNpProfileId());	
				
				toList.setId(activeTransItem.getId());
				toList.setDonorItem(yourItem);
				toList.setNpItem(item);
				toList.setDonorProfile(donorProfile);
				toList.setNpProfile(npProfile);
				toList.setQuantity(activeTransItem.getQuantity());
				toList.setStatusCategory(category);
				activeTransItemsDTO.add(toList);				
			}
		}
		return activeTransItemsDTO;
	}

	@Override
	public void deleteTransaction(Long transactionID) {
		activeTransactionItemsRepository.delete(transactionID);
		log.info("transaction deleted");
		
	}

	@Override
	@Transactional
	public void updateStatus(Long transactionID, String newStatus, int qty) {
		ActiveTransactionItems itemToChange = activeTransactionItemsRepository.findById(transactionID);
		StatusCategory category = statusCategoryRepository.findByStatusCategoryName(newStatus);
		
		if(qty!=itemToChange.getQuantity()){
			itemToChange.setQuantity(qty);
			activeTransactionItemsRepository.save(itemToChange);
			log.info("Active txn quantity updated");			
		}
		if(itemToChange.getDonorItemId() == null && itemToChange.getWishItemId() != null){	
			
				WishlistItem wItem = wishlistItemRepository.findById(itemToChange.getWishItemId());	
				wItem.setQuantityDesired(wItem.getQuantityDesired()-itemToChange.getQuantity());
				wishlistItemRepository.save(wItem);		
				log.info("wishlist qty updated ");
			
		}
		else if(itemToChange.getDonorItemId() != null && itemToChange.getWishItemId() == null){
			
				DonorlistItem dItem = donorlistItemRepository.findById(itemToChange.getDonorItemId());	
				dItem.setQuantity(dItem.getQuantity() - itemToChange.getQuantity());
				donorlistItemRepository.save(dItem);
				log.info("donorlist qty updated ");
		
		}
		
		itemToChange.setStatusCategoryId(category.getId());		
		activeTransactionItemsRepository.save(itemToChange);
	}
	
	@Override
	@Transactional
	public void completeTransaction(Long transactionID){
		ActiveTransactionItems completedItem = activeTransactionItemsRepository.findById(transactionID);
		CompletedTransactions completedTransaction = new CompletedTransactions();


		completedTransaction.setDonorItemId(completedItem.getDonorItemId());	
		completedTransaction.setDonorProfileId(completedItem.getDonorProfileId());
		completedTransaction.setNpProfileId(completedItem.getNpProfileId());
		completedTransaction.setWishlistItemId(completedItem.getWishItemId());
		completedTransaction.setQuantity(completedItem.getQuantity());
		
		completedTransactionRepository.save(completedTransaction);
		activeTransactionItemsRepository.delete(transactionID);
		log.info("Transaction complete");
	
	}

	@Override
	@Transactional
	public void deleteAcceptedTransaction(Long transactionID) {
		// TODO Auto-generated method stub
		ActiveTransactionItems cancelledItem = activeTransactionItemsRepository.findById(transactionID);
		if(cancelledItem.getDonorItemId() == null && cancelledItem.getWishItemId() != null){	
			WishlistItem wItem = wishlistItemRepository.findById(cancelledItem.getWishItemId());
			wItem.setQuantityDesired(wItem.getQuantityDesired() + cancelledItem.getQuantity());
			wishlistItemRepository.save(wItem);	
			log.info("Accepted wishlist withdrawn ");
		}
		else if(cancelledItem.getDonorItemId() != null && cancelledItem.getWishItemId() == null){
			DonorlistItem dItem = donorlistItemRepository.findById(cancelledItem.getDonorItemId());
			dItem.setQuantity(dItem.getQuantity() + cancelledItem.getQuantity());
			donorlistItemRepository.save(dItem);
			log.info("Accepted donorlist withdrawn ");
		}
		activeTransactionItemsRepository.delete(transactionID);
		
	}

	@Override
	@Transactional
	public void updateTransitStatus(Long transactionID, String newStatus) {
		// TODO Auto-generated method stub
		ActiveTransactionItems itemToChange = activeTransactionItemsRepository.findById(transactionID);
		StatusCategory category = statusCategoryRepository.findByStatusCategoryName(newStatus);
		itemToChange.setStatusCategoryId(category.getId());		
		activeTransactionItemsRepository.save(itemToChange);
		
	}

	@Override
	public List<CompletedTransactionsDTO> getCompletedTransactions(Long userId,
			boolean isNP) {
		List<CompletedTransactions> completedTransItems = new ArrayList<CompletedTransactions>();
		List<CompletedTransactionsDTO> completedTransItemsDTO = new ArrayList<CompletedTransactionsDTO>();
		Profile profile = profileRepository.findById(userId);
		if(isNP){
			completedTransItems = completedTransactionRepository.findByNpProfileId(userId);
			for(CompletedTransactions completedTransItem : completedTransItems){
				CompletedTransactionsDTO toList = new CompletedTransactionsDTO();
				WishlistItem yourItem = wishlistItemRepository.findById(completedTransItem.getWishlistItemId());
				DonorlistItem item = donorlistItemRepository.findById(completedTransItem.getDonorItemId());
				
				Profile donorProfile = profileRepository.findById(completedTransItem.getDonorProfileId());
				Profile npProfile = profileRepository.findById(completedTransItem.getNpProfileId());
				
				toList.setId(completedTransItem.getId());
				toList.setNpItemId(yourItem);
				toList.setDonorItemId(item);
				toList.setDonorProfileId(donorProfile);
				toList.setNpProfileId(npProfile);
				toList.setQuantity(completedTransItem.getQuantity());				
				completedTransItemsDTO.add(toList);
			}
		}
		else{
			completedTransItems = 
					completedTransactionRepository.findByDonorProfileId(userId);
			for(CompletedTransactions completedTransItem : completedTransItems){
				CompletedTransactionsDTO toList = new CompletedTransactionsDTO();
				DonorlistItem yourItem = donorlistItemRepository.findById(completedTransItem.getDonorItemId());
				WishlistItem item = wishlistItemRepository.findById(completedTransItem.getWishlistItemId());								
				Profile donorProfile = profileRepository.findById(completedTransItem.getDonorProfileId());
				Profile npProfile = profileRepository.findById(completedTransItem.getNpProfileId());	
				
				toList.setId(completedTransItem.getId());
				toList.setDonorItemId(yourItem);
				toList.setNpItemId(item);
				toList.setDonorProfileId(donorProfile);
				toList.setNpProfileId(npProfile);
				toList.setQuantity(completedTransItem.getQuantity());
				
				completedTransItemsDTO.add(toList);				
			}
		}
		return completedTransItemsDTO;
	}
	
}