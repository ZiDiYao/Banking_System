package com.zidi.banking_application.controller;


import com.zidi.banking_application.dto.BankResponse;
import com.zidi.banking_application.dto.UserRequest;
import com.zidi.banking_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Build account：POST /api/user/create
    @PostMapping("/create")
    public BankResponse createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }
}
