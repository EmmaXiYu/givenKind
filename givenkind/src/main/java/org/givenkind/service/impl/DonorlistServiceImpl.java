/**
 * 
 */
package org.givenkind.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.dto.DonorlistDTO;
import org.givenkind.model.DonorUserLogon;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.ItemCategory;
import org.givenkind.model.UserLogon;
import org.givenkind.model.WishlistItem;
import org.givenkind.repository.DonorlistItemRepository;
import org.givenkind.repository.ItemCategoryRepository;
import org.givenkind.repository.ProfileRepository;
import org.givenkind.repository.UserLogonRepository;
import org.givenkind.service.DonorlistService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author force
 *
 */
@Service
@Transactional
public class DonorlistServiceImpl implements DonorlistService {

	@Inject
	ProfileRepository profileRepository;
	
	@Inject
	DonorlistItemRepository donorlistItemRepository;
	
	private static Logger log = LoggerFactory.getLogger(DonorlistServiceImpl.class);
	
	@Autowired
	UserLogonRepository userRepo;
	
	@Inject
	ItemCategoryRepository ItemCategoryRepository;
	
	@Override
	public List<DonorlistDTO> getListOfDonatedItems(Long userId) {
		ArrayList<DonorlistDTO> list = new ArrayList<DonorlistDTO>();
		
		log.info("user id is "+userId);
		
		UserLogon user = userRepo.findOne(userId);

		List<DonorlistItem> donorlistItems = Collections.emptyList();
		if (user != null) {
			log.info("user not null");
			donorlistItems = donorlistItemRepository.findByDateExpiresAndUserAndQuantity(new Date(),user);
		} else {
			log.info("user null");
		}
		for (DonorlistItem di : donorlistItems) {
			list.add(convertToDTO(di));
		}
		log.info("items "+Arrays.toString(list.toArray()));
		return list;
	}
	
	private DonorlistDTO convertToDTO(DonorlistItem item) {
		DonorlistDTO dto = new DonorlistDTO();
		dto.setCondition(item.getCondition());
		dto.setDateExpires(item.getDateExpires());
		System.out.println("DTO Data:"+dto.getDateExpires());
		dto.setDescription(item.getDescription());
		dto.setFairMarketValue(item.getFairMarketValue());
		dto.setId(item.getId());
		List<String> categories = new ArrayList<String>();
		for(ItemCategory c : item.getItemCategories()) {
			categories.add(c.getCategoryName());
		}
		dto.setItemCategories(categories);
		dto.setItemName(item.getItemName());
		dto.setQuantity(item.getQuantity());
		dto.setUserId(item.getUser().getId());
		return dto;
	}

	@Override
	public void deleteDonatedItem(Long donorlistId) {
		donorlistItemRepository.delete(donorlistId);	
	}
	
	@Inject 
	org.givenkind.repository.DonorUserLogonRepository donorUserLogonRepository;
    @Inject 
    UserLogonRepository userLogonRepository;
	
	
	
	@Override
	@Transactional
	public void adminAddDonatedItem(DonorlistDTO donorlistDTO) {
		DonorlistItem donorItem = new DonorlistItem();
		List<ItemCategory> categories = new ArrayList<ItemCategory>();
		UserLogon user = userLogonRepository.findByLoginId("admin@admin.com");
		
		for(String c : donorlistDTO.getItemCategories()) {
			ItemCategory category = ItemCategoryRepository.findByCategoryName(c);
			categories.add(category);
		}
		
		donorItem.setItemName(donorlistDTO.getItemName());
		donorItem.setDateExpires(donorlistDTO.getDateExpires());
		donorItem.setQuantity(donorlistDTO.getQuantity());
		donorItem.setCondition(donorlistDTO.getCondition());
		donorItem.setFairMarketValue(donorlistDTO.getFairMarketValue());
		donorItem.setDescription(donorlistDTO.getDescription());
		donorItem.setUser(user);
		donorItem.setItemCategories(categories);
		donorlistItemRepository.save(donorItem);
	}

	@Override
	@Transactional
	public void addDonatedItem(DonorlistDTO donorlistDTO) {
		DonorlistItem donorItem = new DonorlistItem();
		List<ItemCategory> categories = new ArrayList<ItemCategory>();
		DonorUserLogon donor = donorUserLogonRepository.findOne(donorlistDTO.getUserId());
		
		for(String c : donorlistDTO.getItemCategories()) {
			ItemCategory category = ItemCategoryRepository.findByCategoryName(c);
			categories.add(category);
		}
		
		donorItem.setItemName(donorlistDTO.getItemName());
		donorItem.setDateExpires(donorlistDTO.getDateExpires());
		donorItem.setQuantity(donorlistDTO.getQuantity());
		donorItem.setCondition(donorlistDTO.getCondition());
		donorItem.setFairMarketValue(donorlistDTO.getFairMarketValue());
		donorItem.setDescription(donorlistDTO.getDescription());
		donorItem.setUser(donor);
		donorItem.setItemCategories(categories);
		donorlistItemRepository.save(donorItem);
	}

	@Override
	@Transactional
	public DonorlistDTO getItemById(Long id) {
		DonorlistItem donorlistItem = donorlistItemRepository.findById(id);
		return convertToDTO(donorlistItem);
	}

	@Override
	@Transactional
	public void editDonatedItem(Long id, DonorlistDTO editedItem) {
	
		DonorlistItem itemToChange = donorlistItemRepository.findById(id);
		itemToChange.setCondition(editedItem.getCondition());
		itemToChange.setDateExpires(editedItem.getDateExpires());
		itemToChange.setDescription(editedItem.getDescription());
		itemToChange.setFairMarketValue(editedItem.getFairMarketValue());
		itemToChange.setItemName(editedItem.getItemName());
		itemToChange.setQuantity(editedItem.getQuantity());		
		List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();
		List<String> itemCategoriesStr = editedItem.getItemCategories();
		for(String item : itemCategoriesStr){
			itemCategories.add(ItemCategoryRepository.findByCategoryName(item));
		}
		itemToChange.setItemCategories(itemCategories);
		donorlistItemRepository.save(itemToChange);
	}

	@Override
	public List<DonorlistDTO> getListOfAllDonatedItems() {
		ArrayList<DonorlistDTO> list = new ArrayList<DonorlistDTO>();
		List<DonorlistItem> donorlistItems=donorlistItemRepository.findAll();
		for (DonorlistItem di : donorlistItems) {
			System.out.println("list");
			list.add(convertToDTO(di));
		}
		return list;
	}

}
