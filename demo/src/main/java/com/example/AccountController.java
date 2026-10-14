package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountRepository repo;

    public AccountController(AccountRepository repo) {
        this.repo = repo;
    }

    // POST /api/accounts  → create a new account
    @PostMapping
    public Account create(@RequestBody Account account) {
        return repo.save(account);
    }

    // GET /api/accounts  → list all
    @GetMapping
    public List<Account> all() {
        return repo.findAll();
    }

    // GET /api/accounts/{id}  → get one
    @GetMapping("/{id}")
    public Account one(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    // POST /api/accounts/{id}/deposit  → add money
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody DepositRequest req) {
        Account acc = repo.findById(id).orElseThrow();
        acc.setBalance(acc.getBalance() + req.amount());
        return repo.save(acc);
    }

    public record DepositRequest(double amount) {}
}