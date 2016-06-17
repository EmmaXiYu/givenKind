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
	}

	@Override
	@Transactional
	public void updateStatus(Long transactionID, String newStatus) {
		ActiveTransactionItems itemToChange = activeTransactionItemsRepository.findById(transactionID);
		StatusCategory category = statusCategoryRepository.findByStatusCategoryName(newStatus);
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
	}
	
}