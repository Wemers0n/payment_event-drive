package com.example.paymentservice.core.services;

import com.example.paymentservice.config.utils.JsonUtil;
import com.example.paymentservice.core.domain.transaction.Transaction;
import com.example.paymentservice.core.dtos.event.Event;
import com.example.paymentservice.core.producer.PaymentProducer;
import com.example.paymentservice.core.respository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private final PaymentProducer producer;
    private final TransactionRepository transactionRepository;
    private final JsonUtil jsonUtil;

    public void sendEvent(Transaction transaction){
        var event = this.createEvent(transaction);
        String payload = jsonUtil.toJson(event);
        producer.sendEvent(payload);
    }

    private Event createEvent(Transaction transaction){
        var transactionId = transactionRepository.findById(transaction.getId()).orElseThrow(() -> new RuntimeException("TransactionId not found"));

        var event = Event.builder()
                .Id(String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID()))
                .transactionId(transactionId)
                .email(transaction.getSender().getEmail())
                .phoneNumber(transaction.getSender().getPhoneNumber())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .timestamp(LocalDateTime.now())
                .build();
        return event;
    }
}
