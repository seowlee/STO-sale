package com.sto.salepurchase.backstosalepurchase.repository;

import com.sto.salepurchase.backstosalepurchase.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
