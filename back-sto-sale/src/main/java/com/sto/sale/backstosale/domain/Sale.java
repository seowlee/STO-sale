package com.sto.sale.backstosale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sto.sale.backstosale.dto.SaleDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale")
@Entity
public class Sale {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long sale_id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_goods_id")
    @JsonIgnore
    private Product product;

    @Id
    private Long sale_goods_id;
    //    private Long sale_goods_id;
    @ColumnDefault("0")
    private Integer sale_cnt;
    @ColumnDefault("0.0")
    private Double sale_rate;

    //    @Builder
//    public Sale(Long sale_id, Product product, Integer sale_cnt, Double sale_rate) {
//        this.sale_id = sale_id;
//        this.product = product;
//        this.sale_cnt = sale_cnt;
//        this.sale_rate = sale_rate;
//    }
//    @Builder
//    public Sale(Product product, Integer sale_cnt, Double sale_rate) {
//        this.product = product;
//        this.sale_cnt = sale_cnt;
//        this.sale_rate = sale_rate;
//    }


    public Sale(Long sale_goods_id, Integer sale_cnt) {
        this.sale_goods_id = sale_goods_id;
        this.sale_cnt = sale_cnt;
    }

    public Sale(SaleDto saleDto) {
        this.sale_goods_id = saleDto.getSale_goods_id();
        this.sale_cnt = saleDto.getSale_cnt();
        this.sale_rate = saleDto.getSale_rate();
        this.product = new Product(saleDto.getSale_goods_id(), saleDto.getTotal_cnt());
    }
}
