package com.sto.sale.backstosale.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class HoldingDto {
    private Long holding_id;
    private Long userId;
    private Long goodsId;
    private Integer goods_cnt;

    //    public void update(Long user_id, Long goods_id, Integer goods_cnt) {
//        this.userId = user_id;
//        this.goodsId = goods_id;
//        this.goods_cnt += goods_cnt;
//    }
    public void update_holding(Integer goods_cnt) {
        this.goods_cnt += goods_cnt;
    }

    public void insert(Long user_id, Long goods_id, Integer goods_cnt) {
        this.userId = user_id;
        this.goodsId = goods_id;
        this.goods_cnt = goods_cnt;
    }
}
