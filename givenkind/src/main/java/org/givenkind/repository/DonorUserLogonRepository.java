package org.givenkind.repository;

import org.givenkind.model.DonorUserLogon;
import org.givenkind.model.UserLogon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorUserLogonRepository extends JpaRepository<DonorUserLogon, Long>{

	public DonorUserLogon findByLoginId(String loginId);
	
	public DonorUserLogon findById(Long id);
	
}
