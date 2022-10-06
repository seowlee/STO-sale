package com.sto.sale.backstosale.domain;

import com.sto.sale.backstosale.dto.TransactionDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transaction_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods_id")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private Integer transaction_cnt;
	private Integer transaction_stat;
	private Date transaction_dt;

	public Transaction(TransactionDto transactionDto) {
		this.transaction_id = transactionDto.getTransactionId();
		this.product = new Product(transactionDto.getGoodsId());
		this.user = new User(transactionDto.getUserId());
		this.transaction_cnt = transactionDto.getTransactionCnt();
		this.transaction_stat = transactionDto.getTransactionStat();
		this.transaction_dt = transactionDto.getTransactionDt();
	}
}
