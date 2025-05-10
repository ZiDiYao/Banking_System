package com.zidi.banking_application.service;

import com.zidi.banking_application.dto.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);
}
