package org.givenkind.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class DonorlistDTO {
	
	private Long id;
	
	private Long userId;
	
	//added wishlistItem
	@NotEmpty
	private String itemName;
	
	@NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateExpires;
	

	@NotNull
	private Integer quantity;
	
	@NotEmpty
	private List<String> itemCategories;
	
	@NotEmpty
	private String condition;

	@NotNull
	private double fairMarketValue;
	
	@NotEmpty
	private String description;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the profileId
	 */
	public Long getUserId(){
		return userId;
	}
	
	/**
	 * @param id the profile id to set
	 */
	public void setUserId(Long id){
		this.userId = id;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the dateExpires
	 */
	public Date getDateExpires() {
		return dateExpires;
	}
	
	/**
	 *@param dateExpires the dateExpires to set 
	 */
	public void setDateExpires(Date dateExpires){
	
	
		this.dateExpires = dateExpires;
		
	}
	
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the donorlist item category
	 */
	public List<String> getItemCategories(){
		return itemCategories;
	}
	
	/**
	 * @param donorlistItemCategory the donorlist item category to set
	 */
	public void setItemCategories(List<String> itemCategories){
		this.itemCategories = itemCategories;
	}
	
	/**
	 * @return the condition of the item
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the fair market value of an item
	 */
	public double getFairMarketValue() {
		return fairMarketValue;
	}

	/**
	 * @param note the note to set
	 */
	public void setFairMarketValue(double fairMarketValue) {
		this.fairMarketValue = fairMarketValue;
	}

	/**
	 * @return the description of an item
	 */
	public String getDescription() {
		return description;
	}

	
	/**
	 * @param impact the impact to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
   
}
