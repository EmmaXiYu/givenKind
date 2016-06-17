package org.givenkind.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.dto.WishlistDTO;
import org.givenkind.model.Profile;
import org.givenkind.model.StatusCategory;
import org.givenkind.model.WishlistItem;
import org.givenkind.model.ActiveTransactionItems;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.repository.ItemCategoryRepository;
import org.givenkind.repository.NonProfitUserLogonRepository;
import org.givenkind.repository.WishlistItemRepository;
import org.givenkind.service.WishlistService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

	private static Logger log = LoggerFactory.getLogger(WishlistServiceImpl.class);
	
	@Inject
	ProfileRepository profileRepository;
	
	@Inject
	WishlistItemRepository wishlistItemRepository;
	
	@Inject
	ItemCategoryRepository ItemCategoryRepository;

	@Inject
	private NonProfitUserLogonRepository nonProfitUserRepository;	
	
	@Override
	public void deleteWish(Long wishId) {
		wishlistItemRepository.delete(wishId);
		
	}

	@Override
	@Transactional
	public void addWish(WishlistDTO wishlistDTO) {
		WishlistItem wishItem = new WishlistItem();
		NonProfitUserLogon user = nonProfitUserRepository.findOne(wishlistDTO.getUserId());
		
//		List<WishlistItem> items = wishlistDTO.getWishlistItems();
//		if(items.size() != 1) {
//			log.error("adding wishlist where #items is not 1 but "+items.size());
//		}
//		for(WishlistItem item : items) {
//			WishlistItem i = new WishlistItem();
//			HashSet<ItemCategory> categories = new HashSet<ItemCategory>();
//			for(i.get)
//		}
		
		
		List<ItemCategory> categories = new ArrayList<ItemCategory>();
		for(String s : wishlistDTO.getWishlistItemCategories()) {
			ItemCategory category = ItemCategoryRepository.findByCategoryName(s);
			categories.add(category);
		}
		
		wishItem.setItemName(wishlistDTO.getItemName());
		wishItem.setDateExpires(wishlistDTO.getDateExpires());
		wishItem.setQuantityDesired(wishlistDTO.getQuantityDesired());
		wishItem.setNotes(wishlistDTO.getNote());
		wishItem.setImpact(wishlistDTO.getImpact());
		wishItem.setNonProfitUserLogon(user);
		
		wishItem.setWishlistItemCategories(categories);
		wishlistItemRepository.save(wishItem);
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
		
		List<String> categories = new ArrayList<String>();
		if(item.getWishlistItemCategories() != null) {
			for(ItemCategory category : item.getWishlistItemCategories()) {
				categories.add(category.getCategoryName());
			}
		}
		dto.setWishlistItemCategories(categories);
		return dto;
	}

	@Override
	@Transactional
	public List<WishlistDTO> getWishesForUser(Long userId) {
		NonProfitUserLogon user = nonProfitUserRepository.findById(userId);
		List<WishlistItem> items = wishlistItemRepository.findByNonProfitUserLogon(user);
		List<WishlistDTO> dtos = new ArrayList<WishlistDTO>(items.size());
		for(WishlistItem item : items) {
			WishlistDTO dto = convertWishlistItemToWishlistDTO(item);
			dtos.add(dto);
		}
		return dtos;
		
	}

	@Override
	@Transactional
	public void editWish(Long wishId, WishlistDTO editedItem) {
		/*Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sefEdit = configuration.buildSessionFactory(builder.buildServiceRegistry());
		Session session = sefEdit.openSession();
		Transaction transaction = session.beginTransaction();*/
		
		WishlistItem itemToChange = wishlistItemRepository.findById(wishId);
		if(itemToChange != null) {
			itemToChange.setDateExpires(editedItem.getDateExpires());
			itemToChange.setImpact(editedItem.getImpact());
			itemToChange.setItemName(editedItem.getItemName());
			itemToChange.setNotes(editedItem.getNote());
			itemToChange.setQuantityDesired(editedItem.getQuantityDesired());
			List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
			List<String> itemCategoriesStr = editedItem.getWishlistItemCategories();
			for(String item : itemCategoriesStr){
				itemCategories.add(ItemCategoryRepository.findByCategoryName(item));
			}
			itemToChange.setWishlistItemCategories(itemCategories);
			wishlistItemRepository.save(itemToChange);
		}
		/*session.merge(itemToChange);
		transaction.commit();
		session.close();*/
		
	}	
}
