package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Holding;
import com.sto.sale.backstosale.dto.HoldingDto;
import com.sto.sale.backstosale.repository.HoldingRepository;
import com.sto.sale.backstosale.repository.ProductRepository;
import com.sto.sale.backstosale.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HoldingService {
	private HoldingRepository holdingRepository;
	private UserRepository userRepository;
	private ProductRepository productRepository;

	public HoldingService(HoldingRepository holdingRepository) {
		this.holdingRepository = holdingRepository;
	}

	@Autowired
	ModelMapper modelMapper;

//	@Autowired
//	HoldingConverter holdingConverter;

	/**
	 * 모든 보유 기록 조회
	 */
	public List<Holding> findAllHoldings() {
		return holdingRepository.findAll();
	}

	/**
	 * 보유 데이터 추가, 업데이트
	 */
	public HoldingDto addHoldingData(HoldingDto addedHoldingDto) {
		HoldingDto holdingDto = holdingRepository
				.findByHoldingData(addedHoldingDto.getUser_id(), addedHoldingDto.getGoods_id());
		System.out.println("=======");
//		System.out.println(holdingRepository
//				.findByHoldingData(addedHolding.getUser_id(), addedHolding.getGoods_id()));

//		ModelMapper modelMapper = new ModelMapper();
		Holding holding = modelMapper.map(holdingDto, Holding.class);
		Holding addedHolding = modelMapper.map(addedHoldingDto, Holding.class);
		System.out.println(addedHolding.toString());

		if (holding != null) {
			holding.update(addedHolding.getGoods_cnt());
		} else {
			holding = addedHolding;
//			holding = modelMapper.map(addedHolding, Holding.class);
//			System.out.println(holding.toString());

//            holding.builder()
//                    .user_id(addedHolding.getUser_id())
//                    .goods_id(addedHolding.getGoods_id())
//                    .goods_cnt(addedHolding.getGoods_cnt())
//                    .build();
		}
		holdingRepository.save(holding);
//		HoldingDto holdingDto = modelMapper.map(holding, HoldingDto.class);

		return addedHoldingDto;
	}


}

