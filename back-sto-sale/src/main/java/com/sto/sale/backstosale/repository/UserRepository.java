package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u.user_id from User u")
	List<Long> findAllByUserIds();
}
