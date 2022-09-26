package com.sto.sale.backstosale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale")
@Entity
public class Sale {
    @Id
    private Long sale_goods_id;
    @ColumnDefault("0")
    private Integer sale_cnt;
    @ColumnDefault("0.0")
    private Double sale_rate;
}
