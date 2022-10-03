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
	public Holding addHoldingData(Holding holding) {
		validateDuplicateUserIdGoodsId(holding);
		holdingRepository.save(holding);
		return holding;
	}

	private void validateDuplicateUserIdGoodsId(Holding holding) {
		holdingRepository.findByHoldingData(holding.getUser_id(), holding.getGoods_id()).orElseGet(Holding::new);

	}

	
}

