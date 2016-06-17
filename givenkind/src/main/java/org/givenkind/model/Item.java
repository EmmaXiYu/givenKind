package org.givenkind.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="ItemType")
public abstract class Item {
	// goal is to force wishlist item and donor item to share the same underlying class
	// so far only this class is defined
	@Id 
	@GeneratedValue
	private Long itemId;
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public UserLogon getUser() {
		return user;
	}

	public void setUser(UserLogon user) {
		this.user = user;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<ItemCategory> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories(List<ItemCategory> itemCategories) {
		this.itemCategories = itemCategories;
	}

	public Date getDateExpires() {
		return dateExpires;
	}

	public void setDateExpires(Date dateExpires) {
		this.dateExpires = dateExpires;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	private UserLogon user;
	
	@Column
	private String itemName;
	
	@Column
	private int quantity;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<ItemCategory> itemCategories;
	
	@Column
	private Date dateExpires;
	
	
}
