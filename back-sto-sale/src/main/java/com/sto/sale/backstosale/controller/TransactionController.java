package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.dto.CancellationSaleDto;
import com.sto.sale.backstosale.dto.TransactionDto;
import com.sto.sale.backstosale.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}


	@GetMapping("/transaction/all")
	public List<TransactionDto> getAllTransactions() {
		List<TransactionDto> transactions = transactionService.findAllTransactions();
		return transactions;
	}

	@PostMapping("/transaction/add")
	public TransactionDto createTransactionData(@RequestBody TransactionDto transactionDto) {
		transactionService.addTransactionData(transactionDto);
		return transactionDto;
	}

	@PostMapping("/transaction/delete")
	public CancellationSaleDto deleteGoodsTransaction(@RequestBody CancellationSaleDto cancellationSaleDto) {
		transactionService.resetGoodsTransaction(cancellationSaleDto);
		return cancellationSaleDto;
	}
}
