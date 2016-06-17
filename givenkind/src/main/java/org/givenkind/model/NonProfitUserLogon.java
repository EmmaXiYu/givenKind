package org.givenkind.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("N")
@Table(name="tblNonProfitUserLogon")
public class NonProfitUserLogon extends UserLogon {
	
	public NonProfitUserLogon() {}
	
	@OneToMany
	private Set<NonProfitCategory> categories;

	public Set<NonProfitCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<NonProfitCategory> categories) {
		this.categories = categories;
	}
 	
	
}
