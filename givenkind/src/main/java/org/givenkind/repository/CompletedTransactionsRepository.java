package org.givenkind.repository;

import java.util.List;

import org.givenkind.model.ActiveTransactionItems;
import org.givenkind.model.CompletedTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedTransactionsRepository extends JpaRepository<CompletedTransactions, Long> {
	
	CompletedTransactions findById(Long id);
	
	List<CompletedTransactions> findByNpProfileId(Long id);

	List<CompletedTransactions> findByDonorProfileId(Long id);
	
}