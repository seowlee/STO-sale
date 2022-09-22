package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
