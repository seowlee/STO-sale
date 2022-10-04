package com.sto.sale.backstosale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "holding")
@Entity
@ToString
public class Holding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holding_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    //    private Long user_id;
    @ManyToOne
    @JoinColumn(name = "goods_id")
    @JsonIgnore
    private Product product;
    //    private Long goods_id;
    private Integer goods_cnt;

    public void update(Integer goods_cnt) {
        this.goods_cnt += goods_cnt;
    }

}
