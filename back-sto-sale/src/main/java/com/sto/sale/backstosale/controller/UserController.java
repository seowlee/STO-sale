package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.dto.UserAccountDto;
import com.sto.sale.backstosale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/ids")
    public List<Long> getAllUserIds() {
        List<Long> userIds = userService.findAllUserIds();
        return userIds;
    }

    @PostMapping("user/update")
    public UserAccountDto updateUserAccount(@RequestBody UserAccountDto userAccountDto) {
        userService.updateUser(userAccountDto);
        return userAccountDto;
    }

}
