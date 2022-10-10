package com.sto.sale.backstosale.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class UserHoldingDto {

	private Long userId;
	private String userNm;
	private String goodsIds;
	private String goodsCnts;
//	private Clob userIds;
	//	private List<String> userIds;
//	private String userIds;

	//    private BigDecimal sumGoodsCnt;
//    private String userIds;
	@QueryProjection
	public UserHoldingDto(Long userId, String userNm, String goodsIds, String goodsCnts) {
		this.userId = userId;
		this.userNm = userNm;
		this.goodsIds = goodsIds;
		this.goodsCnts = goodsCnts;
	}
}
