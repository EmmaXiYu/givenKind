package org.givenkind.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

@Entity @Table(name="tblPasswordReset")
public class PasswordReset {

	
	@Id @GeneratedValue @Column(name="Id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="tblUserLogin_UserId", referencedColumnName="UserId")
	private UserLogon user;
	
	@Column(name="uniqueResetKey")
	private String uniqueResetKey;
	
	public String getUniqueResetKey() {
		return uniqueResetKey;
	}

	public void setUniqueResetKey(String uniqueResetKey) {
		this.uniqueResetKey = uniqueResetKey;
	}

	@Column(name="createdAt")
	private Date createdAt;
	
	public UserLogon getUser() {
		return user;
	}

	public void setUser(UserLogon user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	@PrePersist
	public void onPersist() {
		this.createdAt = new Date();
		this.uniqueResetKey = UUID.randomUUID().toString();
	}
}
