package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(

        @NotBlank(message = "Account holder is required")
        String accountHolder,

        @NotBlank(message = "Account number is required")
        String accountNumber
) {
}