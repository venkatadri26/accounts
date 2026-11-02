package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.dto.CustomerDto;
import com.example.accountmanagementsystem.entity.Account;

import java.util.List;

public interface AccountsService {

    CustomerDto createAccount(CustomerDto customerDto);

    List<CustomerDto> getAllAccounts();

    CustomerDto updateAccount(Long customerId, CustomerDto customerDto);

    void deleteAccount(Long customerId);
}


