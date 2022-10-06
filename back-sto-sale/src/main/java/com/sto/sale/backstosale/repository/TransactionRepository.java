package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
