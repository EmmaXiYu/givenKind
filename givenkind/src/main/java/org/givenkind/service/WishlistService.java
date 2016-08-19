package org.givenkind.service;

import java.util.List;

import org.givenkind.dto.WishlistDTO;


public interface WishlistService {
		
	void deleteWish(Long wishId);
	
	void addWish(WishlistDTO wishlistDTO);
	void adminAddWish(WishlistDTO wishlistDTO);

	void editWish(Long wishId, WishlistDTO editedItem);
	
	List<WishlistDTO> getWishesForUser(Long userId);
	List<WishlistDTO> getAllWishes();
}
