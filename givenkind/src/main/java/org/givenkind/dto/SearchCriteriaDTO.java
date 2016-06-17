package org.givenkind.dto;

import java.util.Arrays;
import java.util.List;

public class SearchCriteriaDTO {
	
	public SearchCriteriaDTO() {}
	
	private String keyword;
	
	private String zipCode;
	
	private int distance;
	
	private int pageNumber = 0;
	
	private List<String> itemCategories;
	
	private List<String> nonprofitCategories;
	
	private Boolean pickUpService;
	
	public String toString() {
		return java.text.MessageFormat.format("{0}(keyword={1}, zipCode={2}, distance={3}, pageNumber={4}, itemCategories={5}, nonprofitCategories={6})", 
				this.getClass().getName(), keyword, zipCode, distance, pageNumber, Arrays.toString(itemCategories.toArray()), Arrays.toString(nonprofitCategories.toArray()));
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<String> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories(List<String> itemCategories) {
		this.itemCategories = itemCategories;
	}

	public Boolean getPickUpService() {
		return pickUpService;
	}

	public void setPickUpService(Boolean pickUpService) {
		this.pickUpService = pickUpService;
	}

	public List<String> getNonprofitCategories() {
		return nonprofitCategories;
	}

	public void setNonprofitCategories(List<String> nonprofitCategories) {
		this.nonprofitCategories = nonprofitCategories;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	
	
}
