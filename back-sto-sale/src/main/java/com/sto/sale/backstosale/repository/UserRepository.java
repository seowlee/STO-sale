package com.sto.sale.backstosale.repository;

import com.sto.sale.backstosale.domain.User;
import com.sto.sale.backstosale.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u.user_id from User u")
	List<Long> findAllByUserIds();

	@Query("select new com.sto.sale.backstosale.dto.UserDto(u.user_id, u.user_account, u.user_nm, u.user_stat)"
			+ "from User u")
	List<UserDto> findByAllUser();

	@Query("select new com.sto.sale.backstosale.dto.UserDto(u.user_id, u.user_account, u.user_nm, u.user_stat)"
			+ "from User u where u.user_id = :id")
	UserDto findByUserId(@Param("id") Long id);

	@Query("select new com.sto.sale.backstosale.dto.UserDto(u.user_id, u.user_account, u.user_nm, u.user_stat)"
			+ "from User u where u.user_stat = 1")
	UserDto findByLoggedInStatUser();
}
