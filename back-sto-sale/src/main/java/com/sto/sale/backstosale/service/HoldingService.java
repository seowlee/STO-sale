package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Holding;
import com.sto.sale.backstosale.repository.HoldingRepository;

import java.util.List;

public class HoldingService {
    private HoldingRepository holdingRepository;

    public HoldingService(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    /**
     * 모든 보유 기록 조회
     */
    public List<Holding> findAllHoldings() {
        return holdingRepository.findAll();
    }

    /**
     * 보유 데이터 추가, 업데이트
     */
    public Holding addHoldingData(Holding addedHolding) {
        Holding holding = holdingRepository
                .findByHoldingData(addedHolding.getUser_id(), addedHolding.getGoods_id());
        if (holding != null) {
            holding.update(addedHolding.getGoods_cnt());
        } else {
            holding = addedHolding;
//            holding.builder()
//                    .user_id(addedHolding.getUser_id())
//                    .goods_id(addedHolding.getGoods_id())
//                    .goods_cnt(addedHolding.getGoods_cnt())
//                    .build();
        }
        holdingRepository.save(holding);
        return holding;
    }


}

