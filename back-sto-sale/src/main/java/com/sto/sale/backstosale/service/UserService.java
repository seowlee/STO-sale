package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.User;
import com.sto.sale.backstosale.dto.UserAccountDto;
import com.sto.sale.backstosale.dto.UserDto;
import com.sto.sale.backstosale.repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 모든 userId 목록 조회
     */
    public List<Long> findAllUserIds() {
        return userRepository.findAllByUserIds();
    }

    /**
     * user 계좌 업데이트
     */
    public UserDto updateUser(UserAccountDto addedUserDto) {
        System.out.println("hihihihiihihhihi");
        System.out.println(addedUserDto);
        UserDto userDto = userRepository.findByUserId(addedUserDto.getUser_id());
        userDto.update_user(addedUserDto.getUser_id(), addedUserDto.getPrice());
        User user = new User(userDto);
        userRepository.save(user);
        return userDto;
    }
}
