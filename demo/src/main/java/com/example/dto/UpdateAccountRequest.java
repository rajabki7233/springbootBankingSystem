package com.example.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;

public record UpdateAccountRequest(

        @NotBlank(message = "Account holder is required")
        String accountHolder
) {
}