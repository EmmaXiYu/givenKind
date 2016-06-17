package org.givenkind.dto;

import java.util.List;

public class SearchResultDTO {
	
	public SearchResultDTO() {}
	
	private String zipCode;
	private long itemId;
	private String itemName;
	private List<String> itemCategories;
	private String itemDetailsUrl;

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public List<String> getItemCategories() {
		return itemCategories;
	}
	public void setItemCategories(List<String> itemCategories) {
		this.itemCategories = itemCategories;
	}
	public String getItemDetailsUrl() {
		return itemDetailsUrl;
	}
	public void setItemDetailsUrl(String itemDetailsUrl) {
		this.itemDetailsUrl = itemDetailsUrl;
	}
	


}
