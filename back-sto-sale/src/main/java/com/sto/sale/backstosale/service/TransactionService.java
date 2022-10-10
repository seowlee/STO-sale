package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Transaction;
import com.sto.sale.backstosale.dto.CancellationSaleDto;
import com.sto.sale.backstosale.dto.TransactionDto;
import com.sto.sale.backstosale.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class TransactionService {

	private TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	/**
	 * 모든 거래 내역 조회
	 */
	public List<TransactionDto> findAllTransactions(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("transaction_dt").descending());
		return transactionRepository.findByAllTransactions(pageable);
	}

	/**
	 * 모든 거래 내역의 총 개수
	 */
	public Long findAllTransactionCnt() {
		Pageable pageable = PageRequest.of(0, 5);
		Page<Transaction> transactionPage = transactionRepository.findByAllTransactionCnt(pageable);
		Long totalCnt = transactionPage.getTotalElements();
		return totalCnt;
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
//		for (TransactionDto dtoAfter : transactionDtos) {
//
//		}
		for (TransactionDto dto : transactionDtos) {
//			dto.cancle_transaction(cancellationSaleDto);
//			Transaction cancleAfter = new Transaction(dto);
//			transactionRepository.save(cancleAfter);
			dto.cancle_previousTransaction(cancellationSaleDto);
			Transaction canclePrevious = new Transaction(dto);
			transactionRepository.save(canclePrevious);
		}

		return transactionDtos;
	}
}
