package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Transaction;
import com.sto.sale.backstosale.dto.TransactionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	@Query("select new com.sto.sale.backstosale.dto.TransactionDto(t.transaction_id, p.goods_id, u.user_id, t.transaction_cnt, t.transaction_stat, t.transaction_dt)"
			+ "from Transaction t join t.product p join t.user u")
	List<TransactionDto> findByAllTransactions();

	@Query("select new com.sto.sale.backstosale.dto.TransactionDto(t.transaction_id, p.goods_id, u.user_id, t.transaction_cnt, t.transaction_stat, t.transaction_dt)"
			+ "from Transaction t join t.product p join t.user u where p.goods_id = :id")
	List<TransactionDto> findBySelectedTransactions(@Param("id") Long id);
}
