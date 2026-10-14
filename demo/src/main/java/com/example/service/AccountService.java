package com.example.service;

import com.example.dto.*;
import com.example.entity.Account;
import com.example.exception.AccountNotFoundException;
import com.example.exception.InsufficientFundsException;
import com.example.repository.AccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(
            CreateAccountRequest request
    ) {

        if (accountRepository
                .existsByAccountNumber(request.accountNumber())) {

            throw new IllegalArgumentException(
                    "Account number already exists"
            );
        }

        Account account = new Account(
                request.accountHolder(),
                request.accountNumber(),
                BigDecimal.ZERO
        );

        Account savedAccount =
                accountRepository.save(account);

        return toResponse(savedAccount);
    }

    public List<AccountResponse> getAllAccounts() {

        return accountRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AccountResponse getAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(id)
                );

        return toResponse(account);
    }

    public AccountResponse updateAccount(
            Long id,
            UpdateAccountRequest request
    ) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(id)
                );

        account.setAccountHolder(
                request.accountHolder()
        );

        Account updated =
                accountRepository.save(account);

        return toResponse(updated);
    }

    public void deleteAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(id)
                );

        accountRepository.delete(account);
    }

    @Transactional
    public AccountResponse deposit(
            Long id,
            TransactionRequest request
    ) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(id)
                );

        BigDecimal newBalance =
                account.getBalance()
                        .add(request.amount());

        account.setBalance(newBalance);

        return toResponse(account);
    }

    @Transactional
    public AccountResponse withdraw(
            Long id,
            TransactionRequest request
    ) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(id)
                );

        if (account.getBalance()
                .compareTo(request.amount()) < 0) {

            throw new InsufficientFundsException();
        }

        BigDecimal newBalance =
                account.getBalance()
                        .subtract(request.amount());

        account.setBalance(newBalance);

        return toResponse(account);
    }

    private AccountResponse toResponse(
            Account account
    ) {

        return new AccountResponse(
                account.getId(),
                account.getAccountHolder(),
                account.getAccountNumber(),
                account.getBalance()
        );
    }
}