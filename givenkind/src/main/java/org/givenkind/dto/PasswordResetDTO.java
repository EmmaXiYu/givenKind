package org.givenkind.dto;

public class PasswordResetDTO {
	public PasswordResetDTO() {}
	
	private long passwordResetAuthorizationId;
	private String uniqueResetKey;
	private String newPassword;
	
	public long getPasswordResetAuthorizationId() {
		return passwordResetAuthorizationId;
	}
	public void setPasswordResetAuthorizationId(long passwordResetAuthorizationId) {
		this.passwordResetAuthorizationId = passwordResetAuthorizationId;
	}
	public String getUniqueResetKey() {
		return uniqueResetKey;
	}
	public void setUniqueResetKey(String uniqueResetKey) {
		this.uniqueResetKey = uniqueResetKey;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
