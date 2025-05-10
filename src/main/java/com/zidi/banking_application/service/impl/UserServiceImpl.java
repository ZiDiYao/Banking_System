package com.zidi.banking_application.service.impl;

import com.zidi.banking_application.dto.AccountInfo;
import com.zidi.banking_application.dto.BankResponse;
import com.zidi.banking_application.dto.EmailDetails;
import com.zidi.banking_application.dto.UserRequest;
import com.zidi.banking_application.entity.User;
import com.zidi.banking_application.mapper.UserMapper;
import com.zidi.banking_application.service.EmailService;
import com.zidi.banking_application.service.UserService;
import com.zidi.banking_application.utils.AccountUtils;
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
    private EmailService emailService;

    @Transactional
    @Override
    public BankResponse createAccount(UserRequest userRequest) {

        // check if the account exits or not
        if (userMapper.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode("409")
                    .responseMessage("Email already exists")
                    .accountInfo(null)
                    .build();
        }

        // build user object
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

        // save user
        userMapper.insertUser(newUser);

        // send email alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(newUser.getEmail())
                .subject("Account CREATION")
                .messageBody("CONGRATUATION Your Account has been Successfully Created.\n Your Account Details:" +
                        "Account Name" + newUser.getFirstName() + " " + newUser.getLastName() + "\n Account Number"
                        + newUser.getAccountNumber())
                .build();
        emailService.sendEmailAlert(emailDetails);

        // build response
        return BankResponse.builder()
                .responseCode("201")
                .responseMessage("Account created successfully")
                .accountInfo(
                        AccountInfo.builder()
                                .accountName(newUser.getFirstName() + " " + newUser.getLastName())
                                .accountNumber(newUser.getAccountNumber())
                                .accountBalance(newUser.getAccountBalance().toPlainString())
                                .build()
                )
                .build();
    }
}
