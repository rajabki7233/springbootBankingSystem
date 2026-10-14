
package com.example.controller;

import com.example.dto.AccountResponse;
import com.example.dto.CreateAccountRequest;
import com.example.dto.UpdateAccountRequest;
import com.example.dto.TransactionRequest;
import com.example.service.AccountService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Create a new account
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {

        AccountResponse response =
                accountService.createAccount(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {

        return ResponseEntity.ok(
                accountService.getAllAccounts()
        );
    }

    // Get one account by ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                accountService.getAccount(id)
        );
    }

    // Update an account
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAccountRequest request) {

        return ResponseEntity.ok(
                accountService.updateAccount(id, request)
        );
    }

    // Delete an account
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable Long id) {

        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    // Deposit money
    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequest request) {

        return ResponseEntity.ok(
                accountService.deposit(id, request)
        );
    }

    // Withdraw money
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequest request) {

        return ResponseEntity.ok(
                accountService.withdraw(id, request)
        );
    }
}