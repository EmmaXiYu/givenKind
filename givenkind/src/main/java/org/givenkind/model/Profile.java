package org.givenkind.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="tblProfile")

public class Profile {
	
	@Id @GeneratedValue @Column(name="ProfileId")
	private Long id;
	
	@Column(name="OrganizationName")
	private String organizationName;
	
	@Column(name="FullName")
	private String fullName;
	
	@Column(name="ContactEmail")
	private String contactEmail;
	
	@Column(name="AddressLine1")
	private String addressLine1;
	
	@Column(name="AddressLine2")
	private String addressLine2;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private State state;
	
	@OneToMany
	private List<NonProfitCategory> categories;

	@Column(name="City")
	private String city;
	
	@Column(name="Country")
	private String country;

	@Column(name="ZipCode")
	private String zipCode;
	
	@Column(name="Phone1")
	private String phone1;
	
	@Column(name="Phone2")
	private String phone2;
	
	@Column(name="Phone3")
	private String phone3;
	
	@Column(name="MissionStatement")
	private String missionStatement;
	
	@Column(name="WebSiteUrl")
	private String webSiteUrl;
	
	@Column(name="IsPickupServiceAvailable")
	private Boolean isPickupServiceAvailable;
	
	@Column(name="TravelDistance")
	private Double travelDistance;
	
	@Column(name="EIN")
	private String ein;
	
	@Column(name="ApprovalStatus")
	private String approvalStatus;
	
	@Column(name="LogoLocation")
	private String logoLocation;
	
	@Column(name="ModifiedDate")
	private Date modifiedDate;
	
	@Column(name="ModifiedBy")
	private String ModifiedBy;
	
	@OneToOne
	private UserLogon user;

	public UserLogon getUser() {
		return user;
	}

	public void setUser(UserLogon user) {
		this.user = user;
	}

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
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	//return the categories to set
	
	public List<NonProfitCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<NonProfitCategory> categories) {
		this.categories = categories;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2 the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the phone3
	 */
	public String getPhone3() {
		return phone3;
	}

	/**
	 * @param phone3 the phone3 to set
	 */
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	/**
	 * @return the missionStatement
	 */
	public String getMissionStatement() {
		return missionStatement;
	}

	/**
	 * @param missionStatement the missionStatement to set
	 */
	public void setMissionStatement(String missionStatement) {
		this.missionStatement = missionStatement;
	}

	/**
	 * @return the webSiteUrl
	 */
	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	/**
	 * @param webSiteUrl the webSiteUrl to set
	 */
	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}

	/**
	 * @return the isPickupServiceAvailable
	 */
	public Boolean getIsPickupServiceAvailable() {
		return isPickupServiceAvailable;
	}

	/**
	 * @param isPickupServiceAvailable the isPickupServiceAvailable to set
	 */
	public void setIsPickupServiceAvailable(Boolean isPickupServiceAvailable) {
		this.isPickupServiceAvailable = isPickupServiceAvailable;
	}

	/**
	 * @return the travelDistance
	 */
	public Double getTravelDistance() {
		return travelDistance;
	}

	/**
	 * @param travelDistance the travelDistance to set
	 */
	public void setTravelDistance(Double travelDistance) {
		this.travelDistance = travelDistance;
	}

	/**
	 * @return the eIN
	 */
	public String getEin() {
		return ein;
	}

	/**
	 * @param eIN the eIN to set
	 */
	public void setEin(String eIN) {
		ein = eIN;
	}

	/**
	 * @return the approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	/**
	 * @return the logoLocation
	 */
	public String getLogoLocation() {
		return logoLocation;
	}

	/**
	 * @param logoLocation the logoLocation to set
	 */
	public void setLogoLocation(String logoLocation) {
		this.logoLocation = logoLocation;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return ModifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	
}
