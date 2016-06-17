package org.givenkind.repository;

import java.util.List;

import org.givenkind.model.DonorlistItem;
import org.givenkind.model.UserLogon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonorlistItemRepository extends JpaRepository<DonorlistItem, Long>, JpaSpecificationExecutor<DonorlistItem> {
	
	DonorlistItem findById(Long id);
	
	List<DonorlistItem> findByUser(UserLogon user);
	
	List<DonorlistItem> findAll();
}
