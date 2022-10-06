package com.sto.sale.backstosale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {
    private Long transactionId;
    private Long userId;
    private Long goodsId;
    private Integer transactionCnt;
    private Integer transactionStat;
    private Date transactionDt;
}
