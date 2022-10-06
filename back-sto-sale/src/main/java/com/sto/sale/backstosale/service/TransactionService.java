package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Transaction;
import com.sto.sale.backstosale.dto.TransactionDto;
import com.sto.sale.backstosale.repository.TransactionRepository;

public class TransactionService {

	private TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	/**
	 * 모든 거래 내역 조회
	 */

	/**
	 * 거래 내역 추가 (insert)
	 */
	public TransactionDto addTransactionData(TransactionDto transactionDto) {
		Transaction transaction = new Transaction(transactionDto);
		transactionRepository.save(transaction);
		return transactionDto;
	}

}
