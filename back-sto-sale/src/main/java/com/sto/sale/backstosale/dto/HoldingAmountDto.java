package com.sto.sale.backstosale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HoldingAmountDto {
	private Long holding_id;
	private Long userId;
	private Long goodsId;
	private Integer unit_amt;
	private Integer goods_cnt;

}
