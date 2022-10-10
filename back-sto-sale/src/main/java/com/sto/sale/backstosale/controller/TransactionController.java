package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.dto.CancellationSaleDto;
import com.sto.sale.backstosale.dto.TransactionDto;
import com.sto.sale.backstosale.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}


	@GetMapping("/transaction/all")
	public List<TransactionDto> getAllTransactions(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		List<TransactionDto> transactions = transactionService.findAllTransactions(page, size);
		return transactions;
	}

	@GetMapping("/transaction/all/count")
	public Long getAllTransactionCnt() {
		Long totalTransactionCnt = transactionService.findAllTransactionCnt();
		return totalTransactionCnt;
	}

	@PostMapping("/transaction/add")
	public TransactionDto createTransactionData(@RequestBody TransactionDto transactionDto) {
		transactionService.addTransactionData(transactionDto);
		return transactionDto;
	}

	@PostMapping("/transaction/cancel")
	public CancellationSaleDto deleteGoodsTransaction(@RequestBody CancellationSaleDto cancellationSaleDto) {
		transactionService.resetGoodsTransaction(cancellationSaleDto);
		return cancellationSaleDto;
	}
}
