package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where p.stat = 0 ")
	List<Product> findByOnSaleProducts();
}
