package org.givenkind.dto;

import java.util.List;


import org.givenkind.model.CompletedTransactions;
import org.givenkind.model.DonorlistItem;
import org.givenkind.model.Profile;
import org.givenkind.model.WishlistItem;

public class CompletedTransactionsDTO {
	
	private Long id;
	
	private Profile donorProfileId ;
	
	private Profile npProfileId;

	private DonorlistItem donorItemId;
	
	private WishlistItem npItemId;
	
	private int quantity;
	
	private List<CompletedTransactions> completedTransactionItems;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profile getDonorProfileId() {
		return donorProfileId;
	}

	public void setDonorProfileId(Profile donorProfileId) {
		this.donorProfileId = donorProfileId;
	}

	public Profile getNpProfileId() {
		return npProfileId;
	}

	public void setNpProfileId(Profile npProfileId) {
		this.npProfileId = npProfileId;
	}

	public DonorlistItem getDonorItemId() {
		return donorItemId;
	}

	public void setDonorItemId(DonorlistItem donorItemId) {
		this.donorItemId = donorItemId;
	}

	public WishlistItem getNpItemId() {
		return npItemId;
	}

	public void setNpItemId(WishlistItem npItemId) {
		this.npItemId = npItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<CompletedTransactions> getCompletedTransactionItems() {
		return completedTransactionItems;
	}

	public void setCompletedTransactionItems(
			List<CompletedTransactions> completedTransactionItems) {
		this.completedTransactionItems = completedTransactionItems;
	}
}
