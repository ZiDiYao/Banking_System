package com.zidi.banking_application.service.impl;

import com.zidi.banking_application.dto.BankResponse;
import com.zidi.banking_application.dto.UserRequest;
import com.zidi.banking_application.entity.User;
import com.zidi.banking_application.service.UserService;
import com.zidi.banking_application.utils.AccountUtils;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService {


    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        /**
         * Creating an account -- saving a new user into the db
         * check if user has already owned an account
         */

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();


        return new BankResponse();
    }
}
