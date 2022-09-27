package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
