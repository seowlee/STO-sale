package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Transaction;
import com.sto.sale.backstosale.dto.CancellationSaleDto;
import com.sto.sale.backstosale.dto.TransactionDto;
import com.sto.sale.backstosale.repository.TransactionRepository;

import java.util.List;

public class TransactionService {

	private TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	/**
	 * 모든 거래 내역 조회
	 */
	public List<TransactionDto> findAllTransactions() {
		return transactionRepository.findByAllTransactions();
	}

	/**
	 * 거래 내역 추가 (insert)
	 */
	public TransactionDto addTransactionData(TransactionDto transactionDto) {
		Transaction transaction = new Transaction(transactionDto);
		transactionRepository.save(transaction);
		return transactionDto;
	}

	/**
	 * 선택된 상품 판매 취소. 거래 취소 내역 추가
	 */
	public List<TransactionDto> resetGoodsTransaction(CancellationSaleDto cancellationSaleDto) {
		System.out.println("delete");
		System.out.println("delete: " + cancellationSaleDto.getGoodsId());
		List<TransactionDto> transactionDtos = transactionRepository.findBySelectedTransactions(cancellationSaleDto.getGoodsId());
		for (TransactionDto dto : transactionDtos) {
			dto.cancle_previousTransaction(cancellationSaleDto);
			Transaction canclePrevious = new Transaction(dto);
			transactionRepository.save(canclePrevious);
			dto.cancle_transaction(cancellationSaleDto);
			Transaction cancleAfter = new Transaction(dto);
			transactionRepository.save(cancleAfter);
		}

		return transactionDtos;
	}
}
