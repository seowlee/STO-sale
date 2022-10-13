package com.sto.sale.backstosale.service;

import com.sto.sale.backstosale.domain.User;
import com.sto.sale.backstosale.dto.*;
import com.sto.sale.backstosale.repository.HoldingRepository;
import com.sto.sale.backstosale.repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;
    private HoldingRepository holdingRepository;

    public UserService(UserRepository userRepository, HoldingRepository holdingRepository) {
        this.userRepository = userRepository;
        this.holdingRepository = holdingRepository;
    }

    /**
     * 모든 userId 목록 조회
     */
    public List<Long> findAllUserIds() {
        return userRepository.findAllByUserIds();
    }

    /**
     * 선택한 유저를 로그인된 유저로 업데이트 (user_stat = 1)
     */
    public UserDto updateUserStat(UserLoggedInDto userLoggedInDto) {
        List<UserDto> allUsers = userRepository.findByAllUser();
        System.out.println("allluserrrrr" + allUsers);
        for (UserDto user : allUsers) {

        }

        UserDto userDto = userRepository.findByUserId(userLoggedInDto.getUserId());
        System.out.println("logggggggg" + userDto);
        userDto.update_stat(userLoggedInDto.getUserId());
        User user = new User(userDto);
        userRepository.save(user);
        return userDto;
    }

    /**
     * user 계좌 업데이트
     */
    public UserDto updateUserAccount(UserAccountDto addedUserDto) {
        System.out.println("hihihihiihihhihi");
        System.out.println(addedUserDto);
        UserDto userDto = userRepository.findByUserId(addedUserDto.getUser_id());
        userDto.update_user(addedUserDto.getUser_id(), addedUserDto.getPrice());
        User user = new User(userDto);
        userRepository.save(user);
        return userDto;
    }

    /**
     * 선택된 상품 판매 취소. 유저 테이블에서 환불 금액 업데이트
     */
    public List<HoldingAmountDto> restoreUserAccount(CancellationSaleDto cancellationSaleDto) {
        System.out.println("refund");
        System.out.println("refund" + cancellationSaleDto);
        List<HoldingAmountDto> holdingAmountDtos = holdingRepository.findByHoldingsAmount(cancellationSaleDto.getGoodsId());
        for (HoldingAmountDto holding : holdingAmountDtos) {
            UserDto userDto = userRepository.findByUserId(holding.getUserId());
            Integer amount = holding.getGoods_cnt() * holding.getUnit_amt();
            userDto.refund_user(holding.getUserId(), amount);
            User user = new User(userDto);
            userRepository.save(user);
        }
        return holdingAmountDtos;
    }
}
