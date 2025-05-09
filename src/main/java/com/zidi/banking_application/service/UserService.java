package com.zidi.banking_application.service;

import com.zidi.banking_application.dto.BankResponse;
import com.zidi.banking_application.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);
}
