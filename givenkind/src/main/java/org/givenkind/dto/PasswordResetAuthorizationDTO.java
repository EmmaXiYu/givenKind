package org.givenkind.dto;

import java.util.Date;

public class PasswordResetAuthorizationDTO {
	public PasswordResetAuthorizationDTO() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUniqueResetKey() {
		return uniqueResetKey;
	}
	public void setUniqueResetKey(String uniqueResetKey) {
		this.uniqueResetKey = uniqueResetKey;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	private long id;
	private String userEmail;
	private String uniqueResetKey;
	private Date createdAt;
	private String passwordResetLink;
	
	public String getPasswordResetLink() {
		return passwordResetLink;
	}

	public void setPasswordResetLink(String passwordResetLink) {
		this.passwordResetLink = passwordResetLink;
	}
}
