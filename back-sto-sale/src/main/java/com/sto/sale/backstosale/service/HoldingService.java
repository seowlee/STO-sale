package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.converter.HoldingConverter;
import com.sto.sale.backstosale.domain.Holding;
import com.sto.sale.backstosale.dto.HoldingDto;
import com.sto.sale.backstosale.repository.HoldingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HoldingService {
    private HoldingRepository holdingRepository;

    public HoldingService(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    HoldingConverter holdingConverter;

    /**
     * 모든 보유 기록 조회
     */
    public List<Holding> findAllHoldings() {
        return holdingRepository.findAll();
    }

    /**
     * 보유 데이터 추가, 업데이트
     */
    public HoldingDto addHoldingData(HoldingDto addedHolding) {
        Holding holding = holdingConverter.convertDtoToEntity(holdingRepository
                .findByHoldingData(addedHolding.getUser_id(), addedHolding.getGoods_id()));
//        System.out.println(holding.toString());
        if (holding != null) {
            holding.update(addedHolding.getGoods_cnt());
        } else {
            holding = holdingConverter.convertDtoToEntity(addedHolding);
            System.out.println(holding.toString());

//            holding.builder()
//                    .user_id(addedHolding.getUser_id())
//                    .goods_id(addedHolding.getGoods_id())
//                    .goods_cnt(addedHolding.getGoods_cnt())
//                    .build();
        }
        holdingRepository.save(holding);
        return holdingConverter.convertEntityToDto(holding);
    }


}

