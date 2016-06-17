package org.givenkind.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity @Table(name="tblItemCategory")
public class ItemCategory {

	@Id @GeneratedValue @Column(name="CategoryId")
	private Long id;
	
	@Column(name="CategoryName")
	private String categoryName;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	private List<DonorlistItem> donorlistItems;
//	
//	public List<DonorlistItem> getDonorlistItems() {
//		return donorlistItems;
//	}
//
//	public void setDonorlistItems(List<DonorlistItem> donorlistItems) {
//		this.donorlistItems = donorlistItems;
//	}

//	public List<WishlistItem> getWishlistItems() {
//		return wishlistItems;
//	}
//
//	public void setWishlistItems(List<WishlistItem> wishlistItems) {
//		this.wishlistItems = wishlistItems;
//	}

//	@ManyToMany(fetch = FetchType.LAZY)
//	private List<WishlistItem> wishlistItems;
	
	@Column(name="CategoryDescription")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
