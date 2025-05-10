package com.zidi.banking_application.listener;

import com.zidi.banking_application.dto.EmailDetails;
import com.zidi.banking_application.entity.User;
import com.zidi.banking_application.event.UserCreatedEvent;
import com.zidi.banking_application.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedListener {

    @Autowired
    private EmailService emailService;
    @Async
    @EventListener
    public void onUserCreated(UserCreatedEvent event) {
        User user = event.getUser();

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("Account CREATION")
                .messageBody("CONGRATULATIONS! Your account has been created.\n\n" +
                        "Account Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                        "Account Number: " + user.getAccountNumber())
                .build();

        emailService.sendEmailAlert(emailDetails);
    }
}
