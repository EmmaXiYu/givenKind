package org.givenkind.repository;

import org.givenkind.model.Profile;
import org.givenkind.model.UserLogon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogonRepository extends JpaRepository<UserLogon, Long>{

	public UserLogon findByLoginId(String loginId);
	
	public UserLogon findByProfile(Profile profile);
	
}
