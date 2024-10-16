package com.example.paymentservice.core.services;

import com.example.paymentservice.config.utils.JsonUtil;
import com.example.paymentservice.core.domain.transaction.EStatus;
import com.example.paymentservice.core.domain.transaction.Transaction;
import com.example.paymentservice.core.domain.user.User;
import com.example.paymentservice.core.domain.user.UserType;
import com.example.paymentservice.core.dtos.event.Event;
import com.example.paymentservice.core.dtos.transaction.TransactionRequestDTO;
import com.example.paymentservice.config.exception.transaction.InvalidTransactionException;
import com.example.paymentservice.config.exception.transaction.UnauthorizedTransactionException;
import com.example.paymentservice.core.producer.PaymentProducer;
import com.example.paymentservice.core.respository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final EventService eventService;

    @Transactional
    public Transaction createTransaction(TransactionRequestDTO transactionRequestDTO) {
        User sender = this.userService.findUserById(transactionRequestDTO.payer());
        User receiver = this.userService.findUserById(transactionRequestDTO.payee());

        validateTransaction(sender, receiver, transactionRequestDTO.value());
        var transaction = saveTransaction(transactionRequestDTO, sender, receiver);

        try {
            if (!authorizationService.authorizeTransaction()) {
                throw new UnauthorizedTransactionException("Unauthorized Transaction");
            }

            updateBalances(sender, receiver, transactionRequestDTO.value());
            transaction.setStatus(EStatus.SUCCESS);

//            this.save(transaction);
            this.userService.saveUser(sender);
            this.userService.saveUser(receiver);
            this.sendEvent(transaction);

        } catch (Exception e) {
            transaction.setStatus(EStatus.FAIL);
//            this.transactionRepository.save(transaction);
            this.sendEvent(transaction);
            throw e;
        }

        return transaction;
    }

    private void validateTransaction(User sender, User receiver, BigDecimal value) {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new InvalidTransactionException("Merchant type user is not authorized to carry out transactions");
        }
        if (sender.getBalance().compareTo(value) < 0) {
            throw new InvalidTransactionException("User does not have enough balance to carry out the transaction");
        }
        if (sender.getId().equals(receiver.getId())) {
            throw new InvalidTransactionException("User cannot send values to himself");
        }
    }

    private Transaction saveTransaction(TransactionRequestDTO transactionRequestDTO, User sender, User receiver) {
        var transaction = Transaction.builder()
                .amount(transactionRequestDTO.value())
                .sender(sender)
                .receiver(receiver)
                .timestamp(LocalDateTime.now())
                .status(EStatus.PENDING)
                .build();
        this.save(transaction);
        return transaction;
    }

    private void updateBalances(User sender, User receiver, BigDecimal value) {
        sender.setBalance(sender.getBalance().subtract(value));
        receiver.setBalance(receiver.getBalance().add(value));
    }

    private void sendEvent(Transaction transaction){
        this.eventService.sendEvent(transaction);
    }

    private void save(Transaction transaction){
        this.transactionRepository.save(transaction);
    }
}

