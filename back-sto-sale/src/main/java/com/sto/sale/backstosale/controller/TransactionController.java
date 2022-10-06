package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.dto.TransactionDto;
import com.sto.sale.backstosale.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/transaction/add")
	public TransactionDto createTransactionData(@RequestBody TransactionDto transactionDto) {
		transactionService.addTransactionData(transactionDto);
		return transactionDto;
	}
}
