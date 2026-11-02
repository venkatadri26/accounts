package com.example.accountmanagementsystem.service;

import com.example.accountmanagementsystem.dto.CustomerDto;
import com.example.accountmanagementsystem.entity.Account;
import com.example.accountmanagementsystem.entity.Customer;
import com.example.accountmanagementsystem.mapper.CustomerMapper;
import com.example.accountmanagementsystem.repository.AccountsRepository;
import com.example.accountmanagementsystem.repository.CustomerRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements  AccountsService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public CustomerDto createAccount(CustomerDto customerDto) {
        Optional<Customer> customerRecord =customerRepository
                .findByMobileNumber(customerDto.getMobileNumber());
        if(customerRecord.isPresent()){
            throw new
                RuntimeException("Account Already Exists on this mobile number:"
                + customerDto.getMobileNumber());
        }
        Customer customer = new Customer();

        Customer record = CustomerMapper.mapToCustomer(customerDto);

        Customer saveRecord = customerRepository.save(record);

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        Account account = new Account();
        account.setCustomerId(saveRecord.getCustomerId());
        account.setAccountType("Savings");
        account.setAccountNumber(randomAccNumber);
        account.setBranchAddress("hoodi");

        Account saveAccountRecord= accountsRepository.save(account);

        CustomerDto responseRecord = new CustomerDto();
        responseRecord.setName(saveRecord.getName());
        responseRecord.setEmail(saveRecord.getEmail());
        responseRecord.setMobileNumber(saveRecord.getMobileNumber());
        responseRecord.setAccount(saveAccountRecord);

        return responseRecord;

    }

    @Override
    public List<CustomerDto> getAllAccounts() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> {
                    Account account = accountsRepository
                            .findByCustomerId(customer.getCustomerId())
                            .orElse(null);
                    return CustomerDto.builder()
                            .name(customer.getName())
                            .email(customer.getEmail())
                            .mobileNumber(customer.getMobileNumber())
                            .account(account)
                            .build();

                })
                .toList();
    }

    @Override
    public CustomerDto updateAccount(
            Long customerId,
            CustomerDto customerDto) {

        // Find existing customer
        Customer existingCustomer = customerRepository
                .findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        // Update customer details
        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setMobileNumber(customerDto.getMobileNumber());

        Customer updatedCustomer =
                customerRepository.save(existingCustomer);

        // Find account using customer ID
        Account existingAccount = accountsRepository
                .findByCustomerId(customerId)
                .orElse(null);

        // Update account details if account exists in request
        if (existingAccount != null && customerDto.getAccount() != null) {

            existingAccount.setAccountType(
                    customerDto.getAccount().getAccountType()
            );

            existingAccount.setBranchAddress(
                    customerDto.getAccount().getBranchAddress()
            );

            existingAccount = accountsRepository.save(existingAccount);
        }

        // Create response
        CustomerDto response = new CustomerDto();

        response.setName(updatedCustomer.getName());
        response.setEmail(updatedCustomer.getEmail());
        response.setMobileNumber(updatedCustomer.getMobileNumber());
        response.setAccount(existingAccount);

        return response;
    }


    @Override
    public void deleteAccount(Long customerId) {

        // Check whether customer exists
        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        // Find account
        Account account = accountsRepository
                .findByCustomerId(customerId)
                .orElse(null);

        // Delete account first
        if (account != null) {
            accountsRepository.delete(account);
        }

        // Delete customer
        customerRepository.delete(customer);
    }
}
