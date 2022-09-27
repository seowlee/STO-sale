package com.sto.sale.backstosale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleDto {
    private Long sale_id;
    private Long sale_goods_id;
    private Integer sale_cnt;
    private Double sale_rate;

}
