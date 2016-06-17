package org.givenkind.repository;

import org.givenkind.model.AdminUserLogon;
import org.givenkind.model.UserLogon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserLogonRepository extends JpaRepository<AdminUserLogon, Long>{

	public AdminUserLogon findByLoginId(String loginId);
	
	public AdminUserLogon findById(Long id);
	
}
