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

}
