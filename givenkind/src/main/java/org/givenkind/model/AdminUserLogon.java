package org.givenkind.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("A")
@Table(name="tblAdminUserLogon")
public class AdminUserLogon extends UserLogon {
	public AdminUserLogon() {}
}
