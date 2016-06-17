package org.givenkind.dto;

import java.util.List;

import org.givenkind.model.ActiveTransactionItems;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.Profile;
import org.givenkind.model.StatusCategory;
import org.givenkind.model.WishlistItem;

public class ActiveTransactionItemsDTO {
	
	private Long id;
	
	private int quantity;
	
	private Profile donorProfile;
	
	private Profile npProfile;
	
	private StatusCategory statusCategory;
	
	private DonorlistItem donorItem;
	
	private WishlistItem npItem;
	
	private List<ActiveTransactionItems> activeTransactionItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Profile getDonorProfile() {
		return donorProfile;
	}

	public void setDonorProfile(Profile donorProfile) {
		this.donorProfile = donorProfile;
	}

	public Profile getNpProfile() {
		return npProfile;
	}

	public void setNpProfile(Profile npProfile) {
		this.npProfile = npProfile;
	}

	public StatusCategory getStatusCategory() {
		return statusCategory;
	}

	public void setStatusCategory(StatusCategory statusCategory) {
		this.statusCategory = statusCategory;
	}

	public DonorlistItem getDonorItem() {
		return donorItem;
	}

	public void setDonorItem(DonorlistItem donorItem) {
		this.donorItem = donorItem;
	}

	public WishlistItem getNpItem() {
		return npItem;
	}

	public void setNpItem(WishlistItem npItem) {
		this.npItem = npItem;
	}

	public List<ActiveTransactionItems> getActiveTransactionItems() {
		return activeTransactionItems;
	}

	public void setActiveTransactionItems(List<ActiveTransactionItems> activeTransactionItems) {
		this.activeTransactionItems = activeTransactionItems;
	}
	
	
}