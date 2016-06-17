package org.givenkind.repository;

import org.givenkind.model.StatusCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusCategoryRepository extends JpaRepository<StatusCategory, Long> {

	StatusCategory findByStatusCategoryName(String statusName);
	
	StatusCategory findById(Long id);
}
