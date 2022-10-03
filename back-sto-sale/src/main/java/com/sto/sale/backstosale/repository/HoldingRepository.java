package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.Holding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoldingRepository extends JpaRepository<Holding, Long> {

	Optional<Holding> findByHoldingData(Long user_id, Long goods_id);
}
