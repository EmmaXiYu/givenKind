package org.givenkind.repository;

import org.givenkind.model.NonProfitUserLogon;
import org.givenkind.model.UserLogon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonProfitUserLogonRepository extends JpaRepository<NonProfitUserLogon, Long>{

	public NonProfitUserLogon findByLoginId(String loginId);
	
	public NonProfitUserLogon findById(Long id);
	
}
