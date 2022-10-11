package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.dto.ProductDto;
import com.sto.sale.backstosale.dto.ProductStatDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select p from Product p where p.stat = 0 ")
//    List<Product> findByOnSaleProducts();

	@Query("select new com.sto.sale.backstosale.dto.ProductDto(p.goods_id, p.goods_nm, p.stat, p.total_amt, p.unit_amt, p.total_cnt, p.order_fee, p.trade_fee, p.created_dt, p.created_by, s.sale_cnt, s.sale_rate)"
			+ "from Product p join p.sale s where p.stat = 0")
	List<ProductDto> findByOnSaleProducts(Pageable pageable);

	@Query("select p from Product p where p.stat = 0")
	Page<Product> findByOnSaleProductsCnt(Pageable pageable);

	@Query("select new com.sto.sale.backstosale.dto.ProductDto(p.goods_id, p.goods_nm, p.stat, p.total_amt, p.unit_amt, p.total_cnt, p.order_fee, p.trade_fee, p.created_dt, p.created_by, s.sale_cnt, s.sale_rate)"
			+ "from Product p join p.sale s where p.stat = 1")
	List<ProductDto> findBySoldOutProducts(Pageable pageable);

	@Query("select p from Product p where p.stat = 1")
	Page<Product> findBySoldOutProductsCnt(Pageable pageable);

	@Query("select new com.sto.sale.backstosale.dto.ProductDto(p.goods_id, p.goods_nm, p.stat, p.total_amt, p.unit_amt, p.total_cnt, p.order_fee, p.trade_fee, p.created_dt, p.created_by, s.sale_cnt, s.sale_rate)"
			+ "from Product p join p.sale s where p.goods_id = :id")
	ProductDto findByProductId(@Param("id") Long id);

	@Query("select new com.sto.sale.backstosale.dto.ProductStatDto(p.goods_id, p.goods_nm, p.stat, p.total_amt, p.sale_amt, p.unit_amt, p.total_cnt, p.order_fee, p.trade_fee, p.sale_fee, p.created_dt, p.updated_dt, p.created_by, p.updated_by, s.sale_cnt, s.sale_rate)"
			+ "from Product p join p.sale s where p.goods_id = :id")
	ProductStatDto findByProductIdWithStat(@Param("id") Long id);

//	select g.goods_id, sum(h.goods_cnt), group_concat(h.user_id)
//	from chloe.goods g
//	join chloe.holding h
//	on g.goods_id = h.goods_id
//	group by h.goods_id

}
