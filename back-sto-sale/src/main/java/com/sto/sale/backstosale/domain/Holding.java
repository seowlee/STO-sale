package com.sto.sale.backstosale.domain;

import com.sto.sale.backstosale.dto.HoldingDto;
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
    private User user;
    //    private Long user_id;
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Product product;
    //    private Long goods_id;
    private Integer goods_cnt;

//	public void update(Integer goods_cnt) {
//		this.goods_cnt += goods_cnt;
//	}


    public Holding(HoldingDto dto) {
        this.holding_id = dto.getHolding_id();
        this.user = new User(dto.getUserId());
        this.product = new Product(dto.getGoodsId());
        this.goods_cnt = dto.getGoods_cnt();
    }
}
