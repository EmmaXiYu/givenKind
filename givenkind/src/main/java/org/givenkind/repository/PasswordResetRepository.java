package org.givenkind.repository;

import org.givenkind.model.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long>{
	public PasswordReset findById(Long id);
	
}
