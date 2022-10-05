package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Sale;
import com.sto.sale.backstosale.dto.SaleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	@Query("select new com.sto.sale.backstosale.dto.SaleDto(s.sale_goods_id, s.sale_cnt, s.sale_rate)"
			+ "from Sale s where s.sale_goods_id = :id")
	SaleDto findBySaleProductId(@Param("id") Long id);

}
