package com.example.notificationservice.core.dtos.transaction;

import com.example.notificationservice.core.dtos.user.User;
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
    private EStatus status;
    private LocalDateTime timestamp;

}
