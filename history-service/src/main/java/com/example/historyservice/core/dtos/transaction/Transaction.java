package com.example.historyservice.core.dtos.transaction;

import com.example.historyservice.core.dtos.user.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    private Long id;
    private BigDecimal amount;
    private User sender;
    private User receiver;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    private LocalDateTime timestamp;

}
