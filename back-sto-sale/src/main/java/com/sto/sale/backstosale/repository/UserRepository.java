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

    @Query("select u from User u where u.user_id = :id")
    UserDto findByUserId(@Param("id") Long id);
}
