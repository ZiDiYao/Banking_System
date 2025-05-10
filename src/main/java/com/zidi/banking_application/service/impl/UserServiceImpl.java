package com.zidi.banking_application.service.impl;

import com.zidi.banking_application.dto.AccountInfo;
import com.zidi.banking_application.dto.BankResponse;
import com.zidi.banking_application.dto.EmailDetails;
import com.zidi.banking_application.dto.UserRequest;
import com.zidi.banking_application.entity.User;
import com.zidi.banking_application.event.UserCreatedEvent;
import com.zidi.banking_application.mapper.UserMapper;
import com.zidi.banking_application.service.EmailService;
import com.zidi.banking_application.service.UserService;
import com.zidi.banking_application.utils.AccountUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zidi.banking_application.mapper.UserMapper;
import java.math.BigDecimal;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public BankResponse createAccount(UserRequest userRequest) {
        if (userMapper.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode("409")
                    .responseMessage("Email already exists")
                    .accountInfo(null)
                    .build();
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .email(userRequest.getEmail())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();

        userMapper.insertUser(newUser);

        //publish the event
        eventPublisher.publishEvent(new UserCreatedEvent(this, newUser));

        return BankResponse.builder()
                .responseCode("201")
                .responseMessage("Account created successfully")
                .accountInfo(AccountInfo.builder()
                        .accountName(newUser.getFirstName() + " " + newUser.getLastName())
                        .accountNumber(newUser.getAccountNumber())
                        .accountBalance(newUser.getAccountBalance().toPlainString())
                        .build())
                .build();
    }
}
