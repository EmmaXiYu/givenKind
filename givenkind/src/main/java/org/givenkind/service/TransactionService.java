package org.givenkind.service;

import java.util.List;

import org.givenkind.dto.ActiveTransactionItemsDTO;
import org.givenkind.model.ActiveTransactionItems;

/**
 * @author force
 *
 */
public interface TransactionService {
	
	List<ActiveTransactionItemsDTO> getActiveTransactions(Long userId, boolean isNP);
	
	void deleteTransaction(Long transactionID);
	
	void updateStatus(Long transactionID, String newStatus);

	void completeTransaction(Long transactionID);

	void startTransaction(ActiveTransactionItemsDTO toDB, boolean isNP);
}