package com.sto.salepurchase.backstosalepurchase.config;

import com.sto.salepurchase.backstosalepurchase.repository.ProductRepository;
import com.sto.salepurchase.backstosalepurchase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final ProductRepository productRepository;

    @Autowired
    public SpringConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository);
    }

}
