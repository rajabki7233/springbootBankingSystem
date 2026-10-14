package com.example.dto;

import java.math.BigDecimal;

public record AccountResponse(
        Long id,
        String accountHolder,
        String accountNumber,
        BigDecimal balance
) {
}