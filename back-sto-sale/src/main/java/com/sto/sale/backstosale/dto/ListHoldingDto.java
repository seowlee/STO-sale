package com.sto.sale.backstosale.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListHoldingDto {

    private Long goodsId;
    private Long sumGoodsCnt;
    private String userIds;

//    private BigDecimal sumGoodsCnt;
//    private String userIds;

}
