package com.example.paymentservice.core.dtos.user;

import com.example.paymentservice.core.domain.user.UserType;

import java.math.BigDecimal;

public record UserRequestDTO(String firstName, String lastName, String document, String phoneNumber, BigDecimal balance, String email, String password, UserType userType) {
}