package com.example.paymentservice.core.dtos.event;

import com.example.paymentservice.core.domain.transaction.EStatus;
import com.example.paymentservice.core.domain.transaction.Transaction;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
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
