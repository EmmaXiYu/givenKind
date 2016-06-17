package org.givenkind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="tblCompletedTransactions")
public class CompletedTransactions {

	@Id @GeneratedValue @Column(name="CompletedTransactionId")
	private Long id;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="DonorProfileId")
	private Long donorProfileId;

	@Column(name="NpProfileId")
	private Long npProfileId;
	
	@Column(name="DonorItemId")
	private Long donorItemId;
	
	@Column(name="WishlistItemId")
	private Long wishlistItemId;
	
	/**
	 * @return the TransactionId
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id){
		this.id = id;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the donorProfile
	 */
	public Long getDonorProfileId(){
		return donorProfileId;
	}
	
	/**
	 * @param donorProfile the donor profile id to set
	 */
	public void setDonorProfileId(Long donorProfileId){
		this.donorProfileId = donorProfileId;
	}
	
	/**
	 * @return the npProfile
	 */
	public Long getNpProfileId(){
		return npProfileId;
	}
	
	/**
	 * @param npProfile the non profit profile id to set
	 */
	public void setNpProfileId(Long npProfileId){
		this.npProfileId = npProfileId;
	}
	
	/**
	 * @return the donorItemId
	 */
	public Long getDonorItemId() {
		return donorItemId;
	}

	/**
	 * @param donorItemId the donor item id to set
	 */
	public void setDonorItemId(Long donorItemId) {
		this.donorItemId = donorItemId;
	}
	
	/**
	 * @return the wishlistItemId
	 */
	public Long getWishlistItemId() {
		return wishlistItemId;
	}

	/**
	 * @param wishlistItemId the wishlist item id to set
	 */
	public void setWishlistItemId(Long wishlistItemId) {
		this.wishlistItemId = wishlistItemId;
	}
}
