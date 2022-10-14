package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Sale;
import com.sto.sale.backstosale.dto.CancellationSaleDto;
import com.sto.sale.backstosale.dto.SaleDto;
import com.sto.sale.backstosale.repository.SaleRepository;

import java.util.List;
import java.util.Optional;

public class SaleService {
	private SaleRepository saleRepository;


	public SaleService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	/**
	 * 상품 판매 정보 테이블에 상품 등록
	 */
	public Long register(Sale sale) {
		int isRegistered = validateDuplicateProduct(sale);
		if (isRegistered == 0) {
			saleRepository.save(sale);
		}
		System.out.println(sale.toString());
		return sale.getProduct().getGoods_id();

	}

	private int validateDuplicateProduct(Sale sale) {
		Optional<Sale> newSale = saleRepository.findById(sale.getProduct().getGoods_id());
		if (newSale.isPresent()) {
			return 1;
		} else return 0;

//				.ifPresent(s -> {
//					throw new IllegalStateException("이미 저장된 상품입니다.");
//				});
	}

	/**
	 * 상품 판매 정보 조회
	 */
	public List<Sale> findSales() {
		return saleRepository.findAll();
	}

	/**
	 * 상품 판매 정보 업데이트
	 */
	public SaleDto addSaleData(SaleDto addedSaleDto) {
		SaleDto saleIdDto = saleRepository.findBySaleProductId(addedSaleDto.getSale_goods_id());
		saleIdDto.update_sale(addedSaleDto.getSale_cnt(), addedSaleDto.getTotal_cnt());
		Sale sale = new Sale(saleIdDto);
//        sale.setSale_goods_id(saleDto.getSale_goods_id());
//        sale.setSale_cnt(sale.getSale_cnt() + saleDto.getSale_cnt());
//        sale.setSale_rate((sale.getSale_cnt() + saleDto.getSale_cnt()) / saleDto.getTotal_cnt() * 100.0);
		saleRepository.save(sale);

		return saleIdDto;
	}

	/**
	 * 선택된 상품 판매 취소. 판매 정보 삭제 (0으로 초기화)
	 */
	public SaleDto resetGoodsSale(CancellationSaleDto cancellationSaleDto) {
		SaleDto saleDto = saleRepository.findBySaleProductId(cancellationSaleDto.getGoodsId());
		saleDto.delete_sale();
		Sale sale = new Sale(saleDto);
		saleRepository.save(sale);
		return saleDto;
	}

}
