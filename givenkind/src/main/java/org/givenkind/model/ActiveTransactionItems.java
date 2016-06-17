package org.givenkind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="tblActiveTransactions")
public class ActiveTransactionItems {

	@Id @GeneratedValue @Column(name="ActiveTransactionId")
	private Long id;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="DonorProfileId")
	private Long donorProfileId;

	@Column(name="NpProfileId")
	private Long npProfileId;

	@Column(name="StatusCategoryId")
	private Long statusCategoryId;
	
	@Column(name="DonorItemId")
	private Long donorItemId;
	
	@Column(name="WishItemId")
	private Long wishItemId;
	
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
	public void setDonorProfile(Long donorProfileId){
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
	public void setNpProfile(Long npProfileId){
		this.npProfileId = npProfileId;
	}

	/**
	 * @return the statusCategoryName
	 */
	public Long getStatusCategoryId() {
		return statusCategoryId;
	}

	/**
	 * @param statusCategoryName the status category name to set
	 */
	public void setStatusCategoryId(Long statusCategoryId) {
		this.statusCategoryId = statusCategoryId;
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
	public void setDonorItem(Long donorItemId) {
		this.donorItemId = donorItemId;
	}
	
	/**
	 * @return the wishlistItemId
	 */
	public Long getWishItemId() {
		return wishItemId;
	}

	/**
	 * @param wishlistItemId the wishlist item id to set
	 */
	public void setWishItemId(Long wishItem) {
		this.wishItemId = wishItem;
	}
}
