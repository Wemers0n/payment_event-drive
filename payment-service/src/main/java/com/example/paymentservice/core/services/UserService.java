package com.example.paymentservice.core.services;

import com.example.paymentservice.core.domain.user.User;
import com.example.paymentservice.core.dtos.user.UserRequestDTO;
import com.example.paymentservice.config.exception.user.UserNotFoundException;
import com.example.paymentservice.core.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findUserById(Long id){
        return this.userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public User createUser(UserRequestDTO data){
        var user = User.builder()
                .firstName(data.firstName())
                .lastName(data.lastName())
                .document(data.document())
                .email(data.email())
                .phoneNumber(data.phoneNumber())
                .password(passwordEncoder.encode(data.password()))
                .balance(data.balance())
                .userType(data.userType())
                .build();

        this.saveUser(user);
        return user;
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
}
