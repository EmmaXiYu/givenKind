package org.givenkind.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class WishlistDTO {

	private Long id;
		
	@NotEmpty
	private String itemName;

	@NotEmpty
	private List<String> wishlistItemCategories;
	
	@NotNull
	private Integer quantityDesired;
	
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateExpires;

	@NotEmpty
	private String note;

	private String impact;

	private Long userId;
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<String> getWishlistItemCategories() {
		return wishlistItemCategories;
	}

	public void setWishlistItemCategories(List<String> wishlistItemCategories) {
		this.wishlistItemCategories = wishlistItemCategories;
	}

	public Integer getQuantityDesired() {
		return quantityDesired;
	}

	public void setQuantityDesired(Integer quantityDesired) {
		this.quantityDesired = quantityDesired;
	}

	public Date getDateExpires() {
		return dateExpires;
	}

	public void setdateExpires(Date dateExpires) {
		this.dateExpires = dateExpires;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getImpact() {
		return impact;
	}
	
	public void setImpact(String impact) {
		this.impact = impact;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userLogonId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userLogonId the userLogonId to set
	 */
	public void setUserId(Long userLogonId) {
		this.userId = userLogonId;
	}
}
