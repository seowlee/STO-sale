package com.sto.sale.backstosale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoldingDto {
    private Long holding_id;
    private Long user_id;
    private Long goods_id;
    private Integer goods_cnt;

//    public void update(Integer goods_cnt) {
//        this.goods_cnt += goods_cnt;
//    }
}
