package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.dto.CancellationSaleDto;
import com.sto.sale.backstosale.dto.UserAccountDto;
import com.sto.sale.backstosale.dto.UserLoggedInDto;
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

    @PostMapping("user/loggedIn")
    public UserLoggedInDto postLoggedInUser(@RequestBody UserLoggedInDto userLoggedInDto) {
        userService.updateUserStat(userLoggedInDto);
        return userLoggedInDto;
    }

    @PostMapping("user/update")
    public UserAccountDto getUpdatedUserAccount(@RequestBody UserAccountDto userAccountDto) {
        userService.updateUserAccount(userAccountDto);
        return userAccountDto;
    }

    @PostMapping("user/delete")
    public CancellationSaleDto getRestoredUserAccount(@RequestBody CancellationSaleDto cancellationSaleDto) {
        userService.restoreUserAccount(cancellationSaleDto);
        return cancellationSaleDto;
    }

}
