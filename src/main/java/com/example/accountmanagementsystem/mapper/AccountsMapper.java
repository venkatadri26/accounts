package com.example.accountmanagementsystem.mapper;

import com.example.accountmanagementsystem.dto.AccountsDto;
import com.example.accountmanagementsystem.entity.Account;

public class AccountsMapper {

    private AccountsMapper() {
    }

    public static AccountsDto mapToAccountsDto(Account account) {

        if (account == null) {
            return null;
        }

        return AccountsDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .branchAddress(account.getBranchAddress())
                .build();
    }

    public static Account mapToAccount(AccountsDto accountsDto) {

        if (accountsDto == null) {
            return null;
        }

        return Account.builder()
                .accountNumber(accountsDto.getAccountNumber())
                .accountType(accountsDto.getAccountType())
                .branchAddress(accountsDto.getBranchAddress())
                .build();
    }

}
