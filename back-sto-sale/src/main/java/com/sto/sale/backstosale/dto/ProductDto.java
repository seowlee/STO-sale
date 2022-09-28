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
public class ProductDto {

    private Long goods_id;
    private String goods_nm;
    private Integer stat;
    //    private Integer total_amt;
//    private Integer sale_amt;
    private Integer total_cnt;
    private Double ordr_fee;
    private Double trade_fee;
    //    private Double sale_fee;
//
//    // java.sql
    private Date created_dt;
    //    private Timestamp updated_dt;
//
    private String created_by;
//    private String updated_by;

    private Integer sale_cnt;
    private Double sale_rate;
}
