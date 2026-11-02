package com.example.accountmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

@Id
@Column(name = "account_number")
private Long accountNumber;

@Column(name = "account_type")
private String accountType;

@Column(name = "branch_address")
private String branchAddress;

@Column(name = "customer_id")
private Long customerId;

}
