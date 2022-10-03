package com.sto.sale.backstosale.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {
	@Id
	private Long user_id;
	private Integer user_account;
	private String user_nm;
}
