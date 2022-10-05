package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.domain.Sale;
import com.sto.sale.backstosale.dto.SaleDto;
import com.sto.sale.backstosale.service.ProductService;
import com.sto.sale.backstosale.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleController {
	private SaleService saleService;
	private ProductService productService;

	@Autowired
	public SaleController(SaleService saleService, ProductService productService) {
		this.saleService = saleService;
		this.productService = productService;
	}

	@GetMapping("/sales/all")
	public List<Sale> getSales() {
		List<Sale> sales = saleService.findSales();
		return sales;
	}

	@PostMapping("/sales/all")
	public List<Sale> initialize() {
		List<Product> p = productService.findAllProducts();

		for (Product product : p) {
			Sale sale = Sale.builder()
					.sale_cnt(0)
					.sale_rate(0.0)
					.product(product)
					.build();
			saleService.register(sale);
		}
		List<Sale> sales = saleService.findSales();
		return sales;
	}

	@PostMapping("/sale/update")
	public SaleDto updateSaleData(@RequestBody SaleDto saleDto) {
		System.out.println("통신 성공");
		System.out.println("sale_goods_id : " + saleDto.getSale_goods_id());
		System.out.println("sale_cnt : " + saleDto.getSale_cnt());
		System.out.println("sale_rate : " + saleDto.getSale_rate());

		saleService.addSaleData(saleDto);
		return saleDto;
	}
}

//                    .sale_goods_id(product.getGoods_id())