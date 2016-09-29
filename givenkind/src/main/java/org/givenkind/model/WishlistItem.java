package org.givenkind.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="tblWishlistItem")
public class WishlistItem {

	@Id @GeneratedValue @Column(name="WishlistItemId")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserLogon nonProfitUserLogon;
	
	@Column(name="ItemName")
	private String itemName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<ItemCategory> wishlistItemCategories;
	
	@Column(name="QuantityDesired")
	private Integer quantityDesired;
	
	@Column(name="Notes")
	private String notes;
	
	@Column(name="Comments")
	private String comments;
	
	@Column(name="DateExpires")
	private Date dateExpires;

	@Column(name="Impact")
	private String impact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserLogon getNonProfitUserLogon() {
		return nonProfitUserLogon;
	}

	public void setNonProfitUserLogon(UserLogon nonProfit) {
		this.nonProfitUserLogon = nonProfit;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<ItemCategory> getWishlistItemCategories() {
		return wishlistItemCategories;
	}

	public void setWishlistItemCategories(List<ItemCategory> wishlistItemCategories) {
		this.wishlistItemCategories = wishlistItemCategories;
	}

	public Integer getQuantityDesired() {
		return quantityDesired;
	}

	public void setQuantityDesired(Integer quantityDesired) {
		this.quantityDesired = quantityDesired;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
		
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateExpires() {
		return dateExpires;
	}

	public void setDateExpires(Date dateExpires) {
		this.dateExpires = dateExpires;
	}

	public void setImpact(String impact) {
		this.impact = impact;
		
	}
	
	public String getImpact() {
		return impact;
	}
}
