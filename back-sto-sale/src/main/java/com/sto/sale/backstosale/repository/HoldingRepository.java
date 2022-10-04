package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HoldingRepository extends JpaRepository<Holding, Long> {

    @Query("select h from Holding h where h.user_id = :user_id and h.goods_id = :goods_id")
    Holding findByHoldingData(@Param("user_id") Long user_id, @Param("goods_id") Long goods_id);

//    Optional<Holding> findByHoldingData(@Param("user_id") Long user_id, @Param("goods_id") Long goods_id);
}
