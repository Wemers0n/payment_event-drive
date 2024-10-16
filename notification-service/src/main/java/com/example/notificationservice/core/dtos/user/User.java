package com.example.notificationservice.core.dtos.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String phoneNumber;
    private String email;
    private String password;
    private BigDecimal balance;
    private UserType userType;
}
