package com.sto.sale.backstosale.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class ListHoldingDto {

	private Long goodsId;
	private String goodsNm;
	private Integer sumGoodsCnt;
	private String userIds;
//	private Clob userIds;
	//	private List<String> userIds;
//	private String userIds;

	//    private BigDecimal sumGoodsCnt;
//    private String userIds;
	@QueryProjection
	public ListHoldingDto(Long goodsId, String goodsNm, Integer sumGoodsCnt, String userIds) {
		this.goodsId = goodsId;
		this.goodsNm = goodsNm;
		this.sumGoodsCnt = sumGoodsCnt;
		this.userIds = userIds;
	}
}
