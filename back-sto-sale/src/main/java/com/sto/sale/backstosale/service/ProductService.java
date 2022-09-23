package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 판매 상품 목록 조회
     */
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    /**
     * 한건조회
     */
    public Product 한건가져오기(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID를 확인해주세요"));
        // findById시 에러가 발생할 수 있기에 람다식으로 에러 처리
    }

}
