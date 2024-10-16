package com.example.notificationservice.core.dtos.event;

import com.example.notificationservice.core.dtos.transaction.EStatus;
import com.example.notificationservice.core.dtos.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    private String Id;
    private Transaction transactionId;
    private String email;
    private String phoneNumber;
    private EStatus status;
    private BigDecimal amount;
    private LocalDateTime timestamp;
}
