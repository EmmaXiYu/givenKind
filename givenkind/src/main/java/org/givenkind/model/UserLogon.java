package org.givenkind.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity 
@Table(name="tblUserLogin")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="UserType")
public abstract class UserLogon {

	@Id @GeneratedValue @Column(name="UserId")
	private Long id;
	
	@Column(name="LoginId")
	private String loginId;
	
	@Column(name="PasswordHash")
	private String passwordHash;

	@Column(name="isActive")
	private Boolean isActive;
	
	@Column(name="LastLoggedInDate")
	private Date lastLoggedInDate;

	@Column(name="CreatedDate")
	private Date createdDate;

	@Column(name="ModifiedDate")
	private Date modifiedDate;

	@Column(name="ModifiedBy")
	private String modifiedBy;

	@ManyToOne
	private UserRole role;
	
	@OneToOne
	private Profile profile;
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
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
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the lastLoggedInDate
	 */
	public Date getLastLoggedInDate() {
		return lastLoggedInDate;
	}

	/**
	 * @param lastLoggedInDate the lastLoggedInDate to set
	 */
	public void setLastLoggedInDate(Date lastLoggedInDate) {
		this.lastLoggedInDate = lastLoggedInDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
}
