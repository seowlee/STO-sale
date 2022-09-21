package com.sto.salepurchase.backstosalepurchase.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goods")
@Entity
public class Product {
	@Id
	private Long goods_id;
	@NotNull
	private String goods_nm;

	@NotNull
	private Integer stat;
	private Integer total_amt;
	private Integer sale_amt;
	private Integer total_cnt;
	private Double ordr_fee;
	private Double trade_fee;
	private Double sale_fee;

	// java.sql
	private Timestamp created_dt;
	private Timestamp updated_dt;

	@Size(max = 20)
	private String created_by;
	@Size(max = 20)
	private String updated_by;


}
