package org.givenkind.repository;

import org.givenkind.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	UserRole findByName(String name);
	
}
