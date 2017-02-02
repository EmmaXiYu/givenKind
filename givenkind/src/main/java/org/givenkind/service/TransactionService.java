package org.givenkind.service;

import java.util.List;

import org.givenkind.dto.ActiveTransactionItemsDTO;
import org.givenkind.dto.CompletedTransactionsDTO;
import org.givenkind.model.ActiveTransactionItems;

/**
 * @author force
 *
 */
public interface TransactionService {
	
	List<ActiveTransactionItemsDTO> getActiveTransactions(Long userId, boolean isNP);
	List<ActiveTransactionItemsDTO> getAllActiveTransactions();
	void deleteTransaction(Long transactionID);
	
	void updateStatus(Long transactionID, String newStatus, int qty);

	void completeTransaction(Long transactionID);

	void startTransaction(ActiveTransactionItemsDTO toDB, boolean isNP);

	void deleteAcceptedTransaction(Long id);

	void updateTransitStatus(Long id, String string);

	List<CompletedTransactionsDTO> getCompletedTransactions(Long userId,
			boolean isNP);
	List<CompletedTransactionsDTO> getAllCompletedTransactions();
}