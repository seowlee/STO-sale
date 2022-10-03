package com.sto.sale.backstosale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "holding")
@Entity
public class Holding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long holding_id;
	private Long user_id;
	private Long goods_id;
	private Integer goods_cnt;
}
