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
    private Long sale_goods_id;
    private Integer sale_cnt;
    private Double sale_rate;
    private Integer total_cnt;

    public void update_sale(Integer sale_cnt, Integer total_cnt) {
//        this.sale_goods_id = saleDto.getSale_goods_id();
        this.sale_cnt += sale_cnt;
        this.sale_rate = 100.0 * this.sale_cnt / total_cnt;
//        System.out.println("salerate============");
//        System.out.println(this.sale_cnt);
//        System.out.println(total_cnt);
//        System.out.println(100.0 * this.sale_cnt / total_cnt);
    }
}
