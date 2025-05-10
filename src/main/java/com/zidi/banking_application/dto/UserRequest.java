package com.zidi.banking_application.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String otherName;
    private String gender;
    private String email;
    private String address;
    private String stateOfOrigin;
    private String phoneNumber;
    private String alternativePhoneNumber;
}
