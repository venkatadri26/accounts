package com.example.accountmanagementsystem.controller;

import com.example.accountmanagementsystem.dto.CustomerDto;

import com.example.accountmanagementsystem.service.AccountsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @PostMapping
    public ResponseEntity<CustomerDto> createAccount(
            @Valid @RequestBody CustomerDto customerDto) {

        CustomerDto response = accountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllPassbooks(){
        List<CustomerDto> passbooks =accountsService.getAllAccounts();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(passbooks);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateAccount(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerDto customerDto) {

        CustomerDto updatedAccount =
                accountsService.updateAccount(
                        customerId,
                        customerDto
                );

        return ResponseEntity
                .ok(updatedAccount);
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteAccount(
            @PathVariable Long customerId) {

        accountsService.deleteAccount(customerId);

        return ResponseEntity
                .ok("Account deleted successfully");
    }
}
