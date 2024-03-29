package com.sto.sale.backstosale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private Long user_id;
	private Integer user_account;
	private String user_nm;
	private Integer user_stat;

	public UserDto(Long user_id) {
		this.user_id = user_id;
	}

	public void update_stat(Long user_id) {
		this.user_id = user_id;
		this.user_stat = 1;
	}

	public void reset_stat() {
		this.user_stat = 0;
	}

	public void update_user(Long user_id, Integer buyPrice) {
		this.user_id = user_id;
		this.user_account -= buyPrice;
	}

	public void refund_user(Long user_id, Integer refundAmount) {
		this.user_id = user_id;
		this.user_account += refundAmount;
	}
}
