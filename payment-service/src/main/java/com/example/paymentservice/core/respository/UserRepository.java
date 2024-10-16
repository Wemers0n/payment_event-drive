package com.example.paymentservice.core.respository;

import com.example.paymentservice.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);

    Optional<User> findUserByFirstName(String firstName);
}
