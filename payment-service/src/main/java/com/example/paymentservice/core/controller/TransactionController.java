package com.example.paymentservice.core.controller;

import com.example.paymentservice.core.domain.transaction.Transaction;
import com.example.paymentservice.core.dtos.transaction.TransactionRequestDTO;
import com.example.paymentservice.core.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequestDTO dto){
        var createdTransaction = this.service.createTransaction(dto);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }
}
