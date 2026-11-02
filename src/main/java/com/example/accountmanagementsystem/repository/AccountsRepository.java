package com.example.accountmanagementsystem.repository;

import com.example.accountmanagementsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository
        extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long customerId);
}
