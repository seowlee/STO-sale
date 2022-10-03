package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select p from Product p where p.stat = 0 ")
//    List<Product> findByOnSaleProducts();

	@Query("select new com.sto.sale.backstosale.dto.ProductDto(p.goods_id, p.goods_nm, p.stat, p.total_amt, p.unit_amt, p.total_cnt, p.order_fee, p.trade_fee, p.created_dt, p.created_by, s.sale_cnt, s.sale_rate)"
			+ "from Product p join p.sale s where p.stat = 0")
	List<ProductDto> findByOnSaleProducts();

	@Query("select new com.sto.sale.backstosale.dto.ProductDto(p.goods_id, p.goods_nm, p.stat, p.total_amt, p.unit_amt, p.total_cnt, p.order_fee, p.trade_fee, p.created_dt, p.created_by, s.sale_cnt, s.sale_rate)"
			+ "from Product p join p.sale s where p.goods_id = :id")
	ProductDto findByProductId(@Param("id") Long id);

}
