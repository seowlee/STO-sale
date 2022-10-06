package com.sto.sale.backstosale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goods")
@Entity
public class Product {
    @Id
    @Column(unique = true, nullable = false)
    private Long goods_id;
    @NotNull
    private String goods_nm;

    @NotNull
    private Integer stat;
    private Integer total_amt;
    private Integer sale_amt;
    private Integer unit_amt;
    private Integer total_cnt;
    private Double order_fee;
    private Double trade_fee;
    private Double sale_fee;

    // java.sql (x)
    // sql timestamp to java.utill Date
    private Date created_dt;
    private Date updated_dt;
//    private Date created_date = new Date(created_dt.getTime());

    @Size(max = 20)
    private String created_by;
    @Size(max = 20)
    private String updated_by;


    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Sale sale;

    public Product(Long goods_id) {
        this.goods_id = goods_id;
    }

    public Product(Long goods_id, Integer total_cnt) {
        this.goods_id = goods_id;
        this.total_cnt = total_cnt;
    }
}
