package com.sto.salepurchase.backstosalepurchase.service;

import com.sto.salepurchase.backstosalepurchase.domain.Product;
import com.sto.salepurchase.backstosalepurchase.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
