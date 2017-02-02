package org.givenkind.repository;

import java.util.List;

import org.givenkind.model.ActiveTransactionItems;
import org.givenkind.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveTransactionItemsRepository extends JpaRepository<ActiveTransactionItems, Long> {
	
	ActiveTransactionItems findById(Long id);
	
	List<ActiveTransactionItems> findByNpProfileId(Long id);

	List<ActiveTransactionItems> findByDonorProfileId(Long id);
	
	List<ActiveTransactionItems> findAll();
	
}