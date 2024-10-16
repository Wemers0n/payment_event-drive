package com.example.paymentservice.core.dtos.transaction;

import java.math.BigDecimal;

public record TransactionRequestDTO(BigDecimal value, Long payer, Long payee) {
}
