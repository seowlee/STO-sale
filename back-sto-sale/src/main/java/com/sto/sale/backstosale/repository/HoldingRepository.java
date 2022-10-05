package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Holding;
import com.sto.sale.backstosale.dto.HoldingDto;
import com.sto.sale.backstosale.dto.ListHoldingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoldingRepository extends JpaRepository<Holding, Long> {

    @Query("select new com.sto.sale.backstosale.dto.HoldingDto(h.holding_id, u.user_id, p.goods_id, h.goods_cnt)"
            + "from Holding h join h.user u join h.product p")
    List<HoldingDto> findByAllHoldings();

    //    select goods_id, sum(goods_cnt), group_concat(user_id) from chloe.holding group by goods_id
    @Query(value = "select new com.sto.sale.backstosale.dto.ListHoldingDto(p.goods_id, sum(h.goods_cnt), group_concat(u.user_id))"
            + "from Holding h join h.user u join h.product p group by p.goods_id")
    List<ListHoldingDto> findByListHolding();
//    @Query("select p.goods_id, sum(h.goods_cnt), group_concat(u.user_id)"
//            + "from Holding h join h.user u join h.product p group by p.goods_id")
//    List<HoldingDto> findByListHolding();

    @Query("select new com.sto.sale.backstosale.dto.HoldingDto(h.holding_id, u.user_id, p.goods_id, h.goods_cnt)"
            + "from Holding h join h.user u join h.product p where u.user_id = :user_id and p.goods_id = :goods_id")
    HoldingDto findByHoldingData(@Param("user_id") Long user_id, @Param("goods_id") Long goods_id);

//	@Query("select h from Holding h where h.user_id = :user_id and h.goods_id = :goods_id")
//	Holding findByHoldingData(@Param("user_id") Long user_id, @Param("goods_id") Long goods_id);
//    Optional<Holding> findByHoldingData(@Param("user_id") Long user_id, @Param("goods_id") Long goods_id);
//    @Query("insert into h from Holding h")
}
