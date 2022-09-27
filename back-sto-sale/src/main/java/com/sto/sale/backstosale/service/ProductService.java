package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.repository.ProductRepository;
import com.sto.sale.backstosale.repository.SaleRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;
    private SaleRepository saleRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 모든 상품 목록 조회
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 판매 상품 목록 조회
     */
    public List<Product> findOnSaleProducts() {
        return productRepository.findByOnSaleProducts();
    }

    /**
     * 한 건 조회
     */
    public Product findOne(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID를 확인해주세요"));
        // findById시 에러가 발생할 수 있기에 람다식으로 에러 처리
    }

    /**
     * join 조회
     */
//    public List<ProductDto> findJoin() {
//        return productRepository.findByJoin();
//    }
}
