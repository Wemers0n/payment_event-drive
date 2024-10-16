package com.example.paymentservice.core.services.security;

import com.example.paymentservice.core.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    public String authenticate(Authentication authentication){
        return jwtService.generateToken(authentication);
    }
}
