package org.givenkind.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.givenkind.model.NonProfitCategory;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = 752029060457867893L;

	@NotEmpty @Email
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String confirmPassword;
	
	@NotEmpty
	private String organizationName;
	
	@NotEmpty
	private String contactPerson;
	
	@NotEmpty
	private String contactEmail;
	
	private String contactPhone;
	
	@NotEmpty
	private String address1;
	
	private String address2;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;
	
	@NotEmpty
	private String zip;
	
	@NotEmpty
	private String missionStatement;
	
	@NotEmpty
	private String website;
	
	@NotNull
	private String pickupService;
	
	@NotNull @Min(0)
	private Double pickupDistance;
	
	@NotEmpty
	private String employerIdentificationNumber;
	
	@NotNull @Size(min=1, max=3)
	private List<String> nonprofitCategories;
	
	@NotEmpty
	private String approvalStatus;
	
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getMissionStatement() {
		return missionStatement;
	}
	public void setMissionStatement(String missionStatement) {
		this.missionStatement = missionStatement;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPickupService() {
		return pickupService;
	}
	public void setPickupService(String pickupService) {
		this.pickupService = pickupService;
	}
	public Double getPickupDistance() {
		return pickupDistance;
	}
	public void setPickupDistance(Double pickupDistance) {
		this.pickupDistance = pickupDistance;
	}
	public String getEmployerIdentificationNumber() {
		return employerIdentificationNumber;
	}
	public void setEmployerIdentificationNumber(String employerIdentificationNumber) {
		this.employerIdentificationNumber = employerIdentificationNumber;
	}
	public List<String> getNonprofitCategories() {
		return nonprofitCategories;
	}
	public void setNonprofitCategories(List<String> nonprofitCategories ) {
		this.nonprofitCategories = nonprofitCategories;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
