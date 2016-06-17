/**
 * 
 */
package org.givenkind.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author force
 *
 */
@Entity @Table(name="tblDonorListItem")
public class DonorlistItem {
	
	@Id @GeneratedValue @Column(name="DonorListItemId")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserLogon user;
	
	@Column(name = "ItemName")
	private String itemName;
	
	@Column(name = "DateExpires")
	private Date dateExpires;
	
	@Column(name = "Quantity")
	private int quantity;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<ItemCategory> itemCategories;
	
	@Column(name = "ItemCondition")
	private String condition;
	
	@Column(name = "FairMarketValue")
	private double fairMarketValue;
	
	@Column(name = "Description")
	private String description;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public UserLogon getUser(){
		return user;
	}
	
	public void setUser(UserLogon user){
		this.user = user;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getDateExpires(){
		return dateExpires;
	}
	
	public void setDateExpires(Date dateExpires) {
		this.dateExpires = dateExpires;
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

	public void setItemCategories(List<ItemCategory> donorListItemCategories) {
		this.itemCategories = donorListItemCategories;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getFairMarketValue() {
		return fairMarketValue;
	}

	public void setFairMarketValue(double fairMarketValue) {
		this.fairMarketValue = fairMarketValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
