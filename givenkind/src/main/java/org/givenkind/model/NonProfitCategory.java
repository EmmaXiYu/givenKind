package org.givenkind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="tblNonProfitCategory")
public class NonProfitCategory {
	
	@Id @GeneratedValue @Column(name="CategoryId")
	private Long id;
	
	@Column(name="CategoryName")
	private String name;
	
	@Column(name="CategoryDescription")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
