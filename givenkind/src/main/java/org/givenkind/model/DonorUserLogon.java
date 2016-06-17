package org.givenkind.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("D")
@Table(name="tblDonorUserLogon")
public class DonorUserLogon extends UserLogon {
	public DonorUserLogon() {}
}
