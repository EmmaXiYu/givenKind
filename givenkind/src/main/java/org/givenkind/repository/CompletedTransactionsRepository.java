package org.givenkind.repository;

import org.givenkind.model.CompletedTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedTransactionsRepository extends JpaRepository<CompletedTransactions, Long> {
	
	CompletedTransactions findById(Long id);
	
}