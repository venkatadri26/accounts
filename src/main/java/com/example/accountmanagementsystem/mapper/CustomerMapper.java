package com.example.accountmanagementsystem.mapper;

import com.example.accountmanagementsystem.dto.CustomerDto;
import com.example.accountmanagementsystem.entity.Customer;

public class CustomerMapper {


    private CustomerMapper() {
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {



        return Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .build();
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {

        if (customer == null) {
            return null;
        }

        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }

}
