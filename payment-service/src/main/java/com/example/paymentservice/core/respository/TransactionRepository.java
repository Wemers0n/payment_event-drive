package com.example.paymentservice.core.respository;

import com.example.paymentservice.core.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
