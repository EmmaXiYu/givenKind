package org.givenkind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="tblStatusCategory")
public class StatusCategory {
	
	@Id @GeneratedValue @Column(name="StatusCategoryId")
	private Long id;
	
	@Column(name="StatusCategoryName")
	private String statusCategoryName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusCategoryName() {
		return statusCategoryName;
	}

	public void setStatusCategoryName(String statusCategoryName) {
		this.statusCategoryName = statusCategoryName;
	}
}